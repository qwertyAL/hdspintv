package com.example.domain.model

data class UserModel(
    val email: String,
    val name: String,
    val userId: Long,
    val userGroup: Int,
    val regDate: String,
    val info: String,
    val foto: String,
    val youWatch: List<String>,
    val fullName: String,
    val favorites: List<String>
)