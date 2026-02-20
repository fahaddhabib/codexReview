package com.example.codexreview.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codexreview.data.datastore.AppLanguage
import com.example.codexreview.data.datastore.LanguagePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val languagePreferences: LanguagePreferences
) : ViewModel() {

    val availableLanguages: List<AppLanguage> = AppLanguage.entries

    val selectedLanguage: StateFlow<AppLanguage> = languagePreferences.appLanguage.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AppLanguage.SYSTEM_DEFAULT
    )

    fun onLanguageSelected(language: AppLanguage) {
        viewModelScope.launch {
            languagePreferences.setAppLanguage(language)
        }
    }
}
