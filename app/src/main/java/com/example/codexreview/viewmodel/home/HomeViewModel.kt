package com.example.codexreview.viewmodel.home

import androidx.lifecycle.ViewModel
import com.example.codexreview.navigation.HomeRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _selectedRoute = MutableStateFlow(HomeRoutes.CHAT)
    val selectedRoute: StateFlow<String> = _selectedRoute.asStateFlow()

    private val _title = MutableStateFlow("Chats")
    val title: StateFlow<String> = _title.asStateFlow()

    fun onTabSelected(route: String) {
        _selectedRoute.value = route
        _title.value = route.toTitle()
    }

    private fun String.toTitle(): String = when (this) {
        HomeRoutes.CHAT -> "Chats"
        HomeRoutes.HUB -> "Hub"
        HomeRoutes.SETTINGS -> "Settings"
        else -> "Chats"
    }
}
