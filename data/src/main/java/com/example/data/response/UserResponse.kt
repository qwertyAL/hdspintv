package com.example.data.response

import com.example.domain.model.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    val name: String,
    @SerialName("user_id") val userId: Long,
    @SerialName("user_group") val userGroup: Int,
    @SerialName("reg_date") val regDate: String,
    val info: String,
    val foto: String,
    @SerialName("youwatch") val youWatch: String,
    @SerialName("fullname") val fullName: String,
    val favorites: String
)

fun UserResponse.toUserModel() = UserModel(
    email,
    name,
    userId,
    userGroup,
    regDate,
    info,
    foto,
    youWatch.split(','),
    fullName,
    favorites.split(',')
)