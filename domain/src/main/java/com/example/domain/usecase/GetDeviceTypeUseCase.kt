package com.example.domain.usecase

import com.example.domain.model.DeviceType
import com.example.domain.repository.LocalStorageRepository

class GetDeviceTypeUseCase(
    private val localStorageRepository: LocalStorageRepository
) {

    operator fun invoke(): DeviceType = localStorageRepository.getDeviceType()

}