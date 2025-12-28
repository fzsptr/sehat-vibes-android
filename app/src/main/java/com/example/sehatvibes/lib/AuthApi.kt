package com.example.sehatvibes.lib

import com.example.sehatvibes.model.LoginRequest
import com.example.sehatvibes.model.LoginResponse
import com.example.sehatvibes.model.RegisterRequest
import com.example.sehatvibes.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ) : retrofit2.Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ) : retrofit2.Response<LoginResponse>
}