package com.example.domain.usecase

import com.example.domain.repository.ApiRepository

class GetMovieListByParameters(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(limit: Int, offset: Int, otherParameters: Map<String, String>) = apiRepository.getMovieListByParameters(limit, offset, otherParameters)

}