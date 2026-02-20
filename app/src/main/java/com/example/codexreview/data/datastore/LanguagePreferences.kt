package com.example.codexreview.data.datastore

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.languageDataStore by preferencesDataStore(name = "language_preferences")

class LanguagePreferences(private val context: Context) {

    private val appLanguageKey = stringPreferencesKey("app_language")

    val appLanguage: Flow<AppLanguage> = context.languageDataStore.data.map { preferences ->
        AppLanguage.fromLanguageTag(preferences[appLanguageKey])
    }

    suspend fun setAppLanguage(language: AppLanguage) {
        context.languageDataStore.edit { preferences ->
            preferences[appLanguageKey] = language.languageTag.orEmpty()
        }
        applyLanguage(language)
    }

    fun applyLanguage(language: AppLanguage) {
        if (language.languageTag == null) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList())
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.languageTag))
        }
    }
}
