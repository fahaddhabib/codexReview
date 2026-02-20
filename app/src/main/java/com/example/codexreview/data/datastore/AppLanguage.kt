package com.example.codexreview.data.datastore

import androidx.annotation.StringRes
import com.example.codexreview.R

enum class AppLanguage(
    val languageTag: String?,
    @StringRes val primaryLabelRes: Int,
    @StringRes val secondaryLabelRes: Int? = null
) {
    SYSTEM_DEFAULT(
        languageTag = null,
        primaryLabelRes = R.string.system_default
    ),
    ENGLISH(
        languageTag = "en",
        primaryLabelRes = R.string.language_english,
        secondaryLabelRes = R.string.native_english
    ),
    CHINESE(
        languageTag = "zh-CN",
        primaryLabelRes = R.string.language_chinese,
        secondaryLabelRes = R.string.native_chinese
    ),
    VIETNAMESE(
        languageTag = "vi",
        primaryLabelRes = R.string.language_vietnamese,
        secondaryLabelRes = R.string.native_vietnamese
    ),
    THAI(
        languageTag = "th",
        primaryLabelRes = R.string.language_thai,
        secondaryLabelRes = R.string.native_thai
    ),
    MALAY(
        languageTag = "ms",
        primaryLabelRes = R.string.language_malay,
        secondaryLabelRes = R.string.native_malay
    ),
    INDONESIAN(
        languageTag = "id",
        primaryLabelRes = R.string.language_indonesian,
        secondaryLabelRes = R.string.native_indonesian
    ),
    HINDI(
        languageTag = "hi",
        primaryLabelRes = R.string.language_hindi,
        secondaryLabelRes = R.string.native_hindi
    ),
    PORTUGUESE(
        languageTag = "pt",
        primaryLabelRes = R.string.language_portuguese,
        secondaryLabelRes = R.string.native_portuguese
    ),
    JAPANESE(
        languageTag = "ja",
        primaryLabelRes = R.string.language_japanese,
        secondaryLabelRes = R.string.native_japanese
    ),
    KOREAN(
        languageTag = "ko",
        primaryLabelRes = R.string.language_korean,
        secondaryLabelRes = R.string.native_korean
    );

    companion object {
        fun fromLanguageTag(languageTag: String?): AppLanguage {
            return entries.firstOrNull { it.languageTag == languageTag } ?: SYSTEM_DEFAULT
        }
    }
}
