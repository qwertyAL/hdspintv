package com.example.hdspintv.di

import com.example.data.repository.ApiRepositoryImpl
import com.example.data.repository.LocalStorageRepositoryImpl
import com.example.domain.repository.ApiRepository
import com.example.domain.usecase.CheckLoginUseCase
import com.example.domain.usecase.GetDeviceTypeUseCase
import com.example.domain.usecase.LoginUserUseCase
import com.example.domain.usecase.SetDeviceTypeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetDeviceTypeUseCase> { GetDeviceTypeUseCase(localStorageRepository = get<LocalStorageRepositoryImpl>()) }

    factory<SetDeviceTypeUseCase> { SetDeviceTypeUseCase(localStorageRepository = get<LocalStorageRepositoryImpl>()) }

    factory<LoginUserUseCase> { LoginUserUseCase(apiRepository = get<ApiRepositoryImpl>()) }

    factory<CheckLoginUseCase> { CheckLoginUseCase(apiRepository = get<ApiRepositoryImpl>()) }

}