package com.example.domain.repository

import com.example.domain.model.ResultModel
import com.example.domain.model.UserModel

interface ApiRepository {

    suspend fun loginUser(name: String, password: String): ResultModel<UserModel>

    suspend fun checkLogin(): Boolean
}