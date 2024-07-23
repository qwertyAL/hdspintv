package com.example.domain.usecase

import com.example.domain.model.DeviceType
import com.example.domain.repository.LocalStorageRepository

class SetDeviceTypeUseCase(
    private val localStorageRepository: LocalStorageRepository
) {

    operator fun invoke(deviceType: DeviceType) {
        localStorageRepository.setDeviceType(deviceType)
    }

}