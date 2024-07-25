package com.example.hdspintv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.example.domain.model.DeviceType
import com.example.hdspintv.ui.theme.HDSpinTVTheme
import com.example.hdspintv.ui.theme.palette
import com.example.tvpresentation.TVApp
import com.example.tvpresentation.screen.Screens
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.checkLogin()
            HDSpinTVTheme {
                val deviceType = remember {
                    mutableStateOf(viewModel.getDeviceType())
                }
//                val loginStatus by viewModel.loginStatus.observeAsState()

                when(deviceType.value) {
                    DeviceType.PHONE -> TVApp() // Заглушка, потом заменить на PhoneApp()
                    DeviceType.TV -> TVApp()
                    DeviceType.UNDEFINED -> ChangeDeviceTypeScreen(
                        onClick = {
                            viewModel.setDeviceType(it)
                            deviceType.value = it
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChangeDeviceTypeScreen(
    onClick: (DeviceType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Выберите тип вашего устройства", color = palette.textPrimary, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onClick(DeviceType.TV) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonColors(
                containerColor = palette.primary,
                contentColor = palette.textPrimary,
                disabledContainerColor = palette.primary,
                disabledContentColor = palette.textPrimary
            )
        ) {
            Text(text = "Смарт ТВ или приставка")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onClick(DeviceType.PHONE) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonColors(
                containerColor = palette.primary,
                contentColor = palette.textPrimary,
                disabledContainerColor = palette.primary,
                disabledContentColor = palette.textPrimary
            )
        ) {
            Text(text = "Телефон")
        }
    }
}

@Composable
@Preview
fun ChangeDeviceTypeScreenPreview() {
    ChangeDeviceTypeScreen({})
}