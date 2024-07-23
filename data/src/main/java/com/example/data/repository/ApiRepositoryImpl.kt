package com.example.data.repository

import com.example.data.response.toUserModel
import com.example.data.source.local.LocalStorageSource
import com.example.data.source.remote.ApiRemoteSource
import com.example.domain.model.ResultModel
import com.example.domain.model.UserModel
import com.example.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiRemoteSource: ApiRemoteSource,
    private val localStorageSource: LocalStorageSource
): ApiRepository {

    override suspend fun loginUser(name: String, password: String): ResultModel<UserModel> {
        val res = apiRemoteSource.loginUser(name, password)
        return if (res.status == ResultModel.Status.SUCCESS) {
            localStorageSource.setLoginData(name, password)
            ResultModel.success(res.data!!.toUserModel())
        } else {
            ResultModel.failure(message = res.message)
        }
    }

    override suspend fun checkLogin(): Boolean {
        val (name, password) = localStorageSource.getLoginData()
        return if (name != null && password != null) {
            val res = loginUser(name, password)
            res.status == ResultModel.Status.SUCCESS
        } else {
            false
        }
    }
}