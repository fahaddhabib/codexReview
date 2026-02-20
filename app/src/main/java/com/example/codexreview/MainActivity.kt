package com.example.codexreview

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.codexreview.data.datastore.AppLanguage
import com.example.codexreview.data.datastore.LanguagePreferences
import com.example.codexreview.navigation.AppNavHost
import com.example.codexreview.ui.theme.CodexreviewTheme
import com.example.codexreview.viewmodel.settings.SettingsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = koinViewModel()
            val languagePreferences: LanguagePreferences = koinInject()

            val selectedTheme = settingsViewModel.selectedTheme.collectAsStateWithLifecycle()
            val selectedLanguage = languagePreferences.appLanguage.collectAsStateWithLifecycle(
                initialValue = AppLanguage.SYSTEM_DEFAULT
            )

            LaunchedEffect(selectedLanguage.value) {
                languagePreferences.applyLanguage(selectedLanguage.value)
            }

            CodexreviewTheme(themeMode = selectedTheme.value) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
