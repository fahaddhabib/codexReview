package com.example.codexreview

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.codexreview.navigation.AppNavHost
import com.example.codexreview.ui.theme.CodexreviewTheme
import com.example.codexreview.viewmodel.settings.SettingsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val settingsViewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                settingsViewModel.selectedLanguageTag.collect { languageTag ->
                    applyLocaleIfNeeded(languageTag)
                }
            }
        }

        setContent {
            val selectedTheme = settingsViewModel.selectedTheme.collectAsStateWithLifecycle()

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

    private fun applyLocaleIfNeeded(languageTag: String) {
        val currentTag = AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag().orEmpty()
        if (currentTag == languageTag) return

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageTag))
    }
}
