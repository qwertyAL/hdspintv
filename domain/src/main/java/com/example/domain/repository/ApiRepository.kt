package com.example.domain.repository

import com.example.domain.model.MovieModel
import com.example.domain.model.ResultModel
import com.example.domain.model.UserModel

interface ApiRepository {

    suspend fun loginUser(name: String, password: String): ResultModel<UserModel>

    suspend fun checkLogin(): Boolean

    suspend fun getMovieListByParameters(limit: Int, offset: Int, otherParameters: Map<String, String>): ResultModel<List<MovieModel>>
}