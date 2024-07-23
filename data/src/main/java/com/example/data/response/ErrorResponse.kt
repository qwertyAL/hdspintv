package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: String
)