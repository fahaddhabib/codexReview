package com.example.codexreview.di

import com.example.codexreview.viewmodel.home.HomeViewModel
import com.example.codexreview.viewmodel.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { SettingsViewModel(get()) }
}
