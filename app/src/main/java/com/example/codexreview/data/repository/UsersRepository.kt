package com.example.codexreview.data.repository

interface UsersRepository {
    suspend fun getUsers(): List<User>
}
