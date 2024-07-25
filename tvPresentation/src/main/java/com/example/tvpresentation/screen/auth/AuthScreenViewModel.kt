package com.example.tvpresentation.screen.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ResultModel
import com.example.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.launch

class AuthScreenViewModel(
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login(onSuccess: () -> Unit) {
        if (name.value != null && password.value != null) {
            viewModelScope.launch {
                val res = loginUserUseCase(name.value!!, password.value!!)
                if (res.status == ResultModel.Status.SUCCESS) {
                    onSuccess()
                } else {
                    _errorMessage.postValue(res.message)
                }
            }
        }
    }
}