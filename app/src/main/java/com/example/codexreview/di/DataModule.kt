package com.example.codexreview.di

import com.example.codexreview.data.datastore.LanguagePreferences
import com.example.codexreview.data.datastore.ThemePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { ThemePreferences(androidContext()) }
    single { LanguagePreferences(androidContext()) }
}
