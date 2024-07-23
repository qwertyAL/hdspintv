package com.example.tvpresentation.screen.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthScreen(vm: AuthScreenViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        val name by vm.name.observeAsState()
        val password by vm.password.observeAsState()
        val errorMessage by vm.errorMessage.observeAsState()
        
        Column(
            modifier = Modifier.padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Войдите в аккаунт")
            if (errorMessage != null && errorMessage != "") {
                Text(text = errorMessage!!, color = Color.Red)
            }
            BasicTextField(
                value = name ?: "",
                onValueChange = { vm.updateName(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            BasicTextField(
                value = password ?: "",
                onValueChange = { vm.updatePassword(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Button(
                onClick = { vm.login {} },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Войти")
            }
        }
    }
}

@Composable
@Preview
fun AuthScreenPreview() {
//    AuthScreen(AuthScreenViewModel())
}