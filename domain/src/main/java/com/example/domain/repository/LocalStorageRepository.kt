package com.example.domain.repository

import com.example.domain.model.DeviceType

interface LocalStorageRepository {

    fun getDeviceType(): DeviceType

    fun setDeviceType(deviceType: DeviceType)

}