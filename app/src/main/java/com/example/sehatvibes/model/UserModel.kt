package com.example.sehatvibes.model

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

data class UserData (
    val id: Int,
    val username: String,
    val password: String,
    val name: String,
    val weight: Double,
    val role: String,
    val createdAt: String
)
