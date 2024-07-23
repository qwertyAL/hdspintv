package com.example.data.repository

import com.example.data.source.local.LocalStorageSource
import com.example.domain.model.DeviceType
import com.example.domain.repository.LocalStorageRepository

class LocalStorageRepositoryImpl(
    private val localStorageSource: LocalStorageSource
): LocalStorageRepository {

    override fun getDeviceType() = localStorageSource.getDeviceType()

    override fun setDeviceType(deviceType: DeviceType) {
        localStorageSource.setDeviceType(deviceType)
    }

}