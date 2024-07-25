package com.example.data.source.remote

import android.util.Log
import com.example.data.ApiRoutes
import com.example.data.response.ErrorResponse
import com.example.data.response.MovieResponse
import com.example.data.response.UserResponse
import com.example.domain.model.ResultModel
import com.example.domain.model.UserModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

class ApiRemoteSource {

    private val xApiKey = "56-81-0e-06-ee"

    private val httpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun loginUser(name: String, password: String): ResultModel<UserResponse> {
        try {
            val request = httpClient.post(ApiRoutes.LOGIN_USER_URL) {
                parameter("x-api-key", xApiKey)
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(FormDataContent(Parameters.build {
                    append("name", name)
                    append("password", password)
                }))
            }

            return when (request.status.value) {
                in 200..299 -> {
                    val response: UserResponse = request.body()
                    ResultModel.success(response)
                }
                in 400..499 -> {
                    val response: ErrorResponse = request.body()
                    ResultModel.failure(message = response.error)
                }
                else -> {
                    ResultModel.failure(message = "Непредвиденная ошибка. Повторите попытку позднее.")
                }
            }
        } catch (e: Exception) {
            return ResultModel.failure(message = "Непредвиденная ошибка. Повторите попытку позднее.")
        }
    }

    suspend fun getMovieListByParameters(limit: Int, offset: Int, otherParameters: Map<String, String>): ResultModel<List<MovieResponse>> {
        try {
            val request = httpClient.get(ApiRoutes.MOVIE_URL) {
                parameter("x-api-key", xApiKey)
                parameter("limit", limit)
//                parameter("offset", offset) НУЖНО НА СЕРВАКЕ ОФФСЕТ ПРИКРУТИТЬ
                headers {
                    otherParameters.forEach {
                        append(it.key, it.value)
                    }
                }
            }

            return when(request.status.value) {
                in 200..299 -> {
                    val response: List<MovieResponse> = request.body()
                    ResultModel.success(response)
                }
                in 400..499 -> {
                    val response: ErrorResponse = request.body()
                    ResultModel.failure(message = request.body())
                }
                else -> {
                    ResultModel.failure(message = "Непредвиденная ошибка. Попробуйте позднее.")
                }
            }
        } catch (e: Exception) {
            return ResultModel.failure(message = "Непредвиденная ошибка. Повторите попытку позднее.")
        }
    }

}