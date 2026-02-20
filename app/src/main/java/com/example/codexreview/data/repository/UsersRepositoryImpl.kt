package com.example.codexreview.data.repository

import com.example.codexreview.data.api.UsersApi
import com.example.codexreview.data.api.toDomain

class UsersRepositoryImpl(
    private val usersApi: UsersApi
) : UsersRepository {

    override suspend fun getUsers(): List<User> = usersApi.getUsers().map { it.toDomain() }
}
