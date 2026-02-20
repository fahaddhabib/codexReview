package com.example.codexreview.data.api

import com.example.codexreview.data.repository.User

data class UserDto(
    val id: Int,
    val name: String,
    val email: String
)

fun UserDto.toDomain(): User = User(
    id = id,
    name = name,
    email = email
)
