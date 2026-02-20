package com.example.codexreview.data.api

import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
