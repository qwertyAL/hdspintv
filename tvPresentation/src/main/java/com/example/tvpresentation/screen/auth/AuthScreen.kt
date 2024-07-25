package com.example.tvpresentation.screen.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Text
import com.example.tvpresentation.palette
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthScreen(vm: AuthScreenViewModel = koinViewModel(), onSuccessAuth: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = palette.background),
        contentAlignment = Alignment.Center
    ) {
        val name by vm.name.observeAsState()
        val password by vm.password.observeAsState()
        val errorMessage by vm.errorMessage.observeAsState()
        
        Column(
            modifier = Modifier.padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Войдите в аккаунт", color = palette.textPrimary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            if (errorMessage != null && errorMessage != "") {
                Text(text = errorMessage!!, color = Color.Red, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(20.dp))
            }
            OutlinedTextField(
                value = name ?: "",
                onValueChange = { vm.updateName(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = palette.textPrimary,
                    unfocusedTextColor = palette.textPrimary,
                    focusedIndicatorColor = palette.primary,
                    unfocusedIndicatorColor = palette.textPrimary,
                    focusedLabelColor = palette.primary,
                    unfocusedLabelColor = palette.textPrimary,
                    focusedContainerColor = palette.background,
                    unfocusedContainerColor = palette.background
                ),
                label = {
                    Text(text = "Имя", color = palette.textPrimary)
                },
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = password ?: "",
                onValueChange = { vm.updatePassword(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = palette.textPrimary,
                    unfocusedTextColor = palette.textPrimary,
                    focusedIndicatorColor = palette.primary,
                    unfocusedIndicatorColor = palette.textPrimary,
                    focusedLabelColor = palette.primary,
                    unfocusedLabelColor = palette.textPrimary,
                    focusedContainerColor = palette.background,
                    unfocusedContainerColor = palette.background
                ),
                label = {
                    Text(text = "Пароль", color = palette.textPrimary)
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { vm.login {
                    onSuccessAuth()
                } },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.colors(
                    focusedContainerColor = palette.primary,
                    focusedContentColor = palette.textPrimary
                ),
                shape = ButtonDefaults.shape(shape = RoundedCornerShape(10.dp))
            ) {
                Text(text = "Войти")
            }
        }
    }
}

@Composable
@Preview
fun AuthScreenPreview() {
    AuthScreen(onSuccessAuth = {})
}