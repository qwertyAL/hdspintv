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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.example.domain.model.DeviceType
import com.example.hdspintv.ui.theme.HDSpinTVTheme
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
                val loginStatus by viewModel.loginStatus.observeAsState()

                when(deviceType.value) {
                    DeviceType.PHONE -> TVApp(if (loginStatus == true) { Screens.Main() } else { Screens.Auth() }) // Заглушка, потом заменить на PhoneApp()
                    DeviceType.TV -> TVApp(if (loginStatus == true) { Screens.Main() } else { Screens.Auth() })
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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onClick(DeviceType.TV) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Смарт ТВ или приставка")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onClick(DeviceType.PHONE) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
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