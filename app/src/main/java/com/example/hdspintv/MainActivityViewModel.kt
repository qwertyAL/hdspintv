package com.example.hdspintv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DeviceType
import com.example.domain.usecase.CheckLoginUseCase
import com.example.domain.usecase.GetDeviceTypeUseCase
import com.example.domain.usecase.SetDeviceTypeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val getDeviceTypeUseCase: GetDeviceTypeUseCase,
    private val setDeviceTypeUseCase: SetDeviceTypeUseCase,
    private val checkLoginUseCase: CheckLoginUseCase
): ViewModel() {

    private val _loginStatus = MutableLiveData(false)
    val loginStatus: LiveData<Boolean> = _loginStatus

    fun getDeviceType() = getDeviceTypeUseCase()

    fun setDeviceType(deviceType: DeviceType) {
        setDeviceTypeUseCase(deviceType)
    }

    fun checkLogin() {
        viewModelScope.launch {
            _loginStatus.postValue(checkLoginUseCase())
        }
    }

}