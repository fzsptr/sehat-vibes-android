package com.example.sehatvibes.model

import com.google.gson.annotations.SerializedName

data class ResponseError (
    val status: String,
    val message: String,
)

data class RegisterRequest(
    val username: String,
    val password: String,
    val name: String,
    val weight: Double
)

data class RegisterResponse(
    val status: String,
    val message: String,
    val data: UserData?
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val status: String,
    val message: String,
    val data: TokenData?
)

data class UserResponse(
    val status: String,
    val message: String,
    val data: UserData?
)

data class TokenData(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("expires_in")
    val expiresIn: String,

    val user: LoginUser
)

data class LoginUser(
    val id: Int,
    val username: String,
    val name: String,
    val role: String
)

data class UserData (
    val id: Int,
    val username: String,
    val name: String,
    val weight: Double,
    val role: String,
    val createdAt: String
)
