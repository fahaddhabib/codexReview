package com.example.codexreview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codexreview.data.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState(isLoading = true))
    val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            runCatching { usersRepository.getUsers() }
                .onSuccess { users ->
                    _uiState.value = UsersUiState(
                        isLoading = false,
                        users = users,
                        errorMessage = null
                    )
                }
                .onFailure { throwable ->
                    _uiState.value = UsersUiState(
                        isLoading = false,
                        users = emptyList(),
                        errorMessage = throwable.message ?: "Something went wrong"
                    )
                }
        }
    }
}
