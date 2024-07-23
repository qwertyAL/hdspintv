package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class CheckLoginUseCase(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(): Boolean {
        return apiRepository.checkLogin()
    }

}