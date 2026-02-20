package com.example.codexreview.viewmodel

import com.example.codexreview.data.repository.User

data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null
)
