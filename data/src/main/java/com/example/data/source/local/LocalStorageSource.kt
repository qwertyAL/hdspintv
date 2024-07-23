package com.example.data.source.local

import android.content.Context
import com.example.domain.model.DeviceType

class LocalStorageSource(
    private val context: Context
) {

    fun setDeviceType(deviceType: DeviceType) {
        context
            .getSharedPreferences("deviceType", Context.MODE_PRIVATE)
            .edit()
            .putString("type", if (deviceType == DeviceType.TV) { "tv" } else { "phone" })
            .apply()
    }

    fun getDeviceType(): DeviceType {
        val deviceTypeString = context
            .getSharedPreferences("deviceType", Context.MODE_PRIVATE)
            .getString("type", null)

        return when (deviceTypeString) {
            "tv" -> DeviceType.TV
            "phone" -> DeviceType.PHONE
            else -> DeviceType.UNDEFINED
        }
    }

    fun setLoginData(name: String, password: String) {
        context
            .getSharedPreferences("loginData", Context.MODE_PRIVATE)
            .edit()
            .putString("name", name)
            .putString("password", password)
            .apply()
    }

    fun getLoginData(): Pair<String?, String?> {
        val sp = context.getSharedPreferences("loginData", Context.MODE_PRIVATE)
        return Pair(sp.getString("name", null), sp.getString("password", null))
    }

}