package com.example.data.repository

import com.example.data.response.toMovieModel
import com.example.data.response.toUserModel
import com.example.data.source.local.LocalStorageSource
import com.example.data.source.remote.ApiRemoteSource
import com.example.domain.model.MovieModel
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

    override suspend fun getMovieListByParameters(
        limit: Int,
        offset: Int,
        otherParameters: Map<String, String>
    ): ResultModel<List<MovieModel>> {
        val res = apiRemoteSource.getMovieListByParameters(limit, offset, otherParameters)

        return if (res.status == ResultModel.Status.SUCCESS) {
            ResultModel.success(res.data!!.map { it.toMovieModel() })
        } else {
            ResultModel.failure(message = null)
        }
    }
}