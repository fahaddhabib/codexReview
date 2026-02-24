package com.example.codexreview.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codexreview.data.datastore.LanguagePreferences
import com.example.codexreview.data.datastore.ThemeMode
import com.example.codexreview.data.datastore.ThemePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val themePreferences: ThemePreferences,
    private val languagePreferences: LanguagePreferences
) : ViewModel() {

    val selectedTheme: StateFlow<ThemeMode> = themePreferences.themeMode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ThemeMode.SYSTEM
    )

    val selectedLanguageTag: StateFlow<String> = languagePreferences.selectedLanguageTag.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = "en"
    )

    fun onThemeSelected(mode: ThemeMode) {
        viewModelScope.launch {
            themePreferences.setThemeMode(mode)
        }
    }

    fun onLanguageSelected(languageTag: String) {
        if (languageTag == selectedLanguageTag.value) return

        viewModelScope.launch {
            languagePreferences.setSelectedLanguageTag(languageTag)
        }
    }
}
