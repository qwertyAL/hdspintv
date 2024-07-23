package com.example.domain.usecase

import com.example.domain.model.ResultModel
import com.example.domain.model.UserModel
import com.example.domain.repository.ApiRepository

class LoginUserUseCase(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(name: String, password: String): ResultModel<UserModel> {
        return apiRepository.loginUser(name, password)
    }

}