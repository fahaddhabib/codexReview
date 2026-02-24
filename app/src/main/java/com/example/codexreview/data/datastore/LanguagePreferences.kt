package com.example.codexreview.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DEFAULT_LANGUAGE_TAG = "en"
private val Context.languageDataStore by preferencesDataStore(name = "language_preferences")

class LanguagePreferences(
    private val context: Context
) {
    private val selectedLanguageKey = stringPreferencesKey("selected_language")

    val selectedLanguageTag: Flow<String> = context.languageDataStore.data.map { preferences ->
        preferences[selectedLanguageKey] ?: DEFAULT_LANGUAGE_TAG
    }

    suspend fun setSelectedLanguageTag(languageTag: String) {
        context.languageDataStore.edit { preferences ->
            if (preferences[selectedLanguageKey] != languageTag) {
                preferences[selectedLanguageKey] = languageTag
            }
        }
    }
}
