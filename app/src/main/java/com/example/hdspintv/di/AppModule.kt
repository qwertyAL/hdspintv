package com.example.hdspintv.di

import com.example.hdspintv.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainActivityViewModel> { MainActivityViewModel(getDeviceTypeUseCase = get(), setDeviceTypeUseCase = get(), checkLoginUseCase = get()) }

}