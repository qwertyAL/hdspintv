package com.example.tvpresentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.domain.usecase.CheckLoginUseCase
import com.example.tvpresentation.screen.main.MainScreen
import com.example.tvpresentation.screen.Screens
import com.example.tvpresentation.screen.auth.AuthScreen
import com.example.tvpresentation.screen.auth.AuthScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.currentKoinScope
import org.koin.compose.koinInject
import org.koin.java.KoinJavaComponent.get

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun TVApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Main(),
        builder = {
            composable(
                route = Screens.Main()
            ) {
                MainScreen()
            }

            composable(
                route = Screens.Auth()
            ) {
                val vm = koinViewModel<AuthScreenViewModel>()
                AuthScreen(vm = vm, onSuccessAuth = {
                    navController.navigate(Screens.Main()) {
                        popUpTo(0)
                    }
                })
            }
        }
    )
}

@Composable
@Preview
fun TVAppPreview() {
//    TVApp()
}