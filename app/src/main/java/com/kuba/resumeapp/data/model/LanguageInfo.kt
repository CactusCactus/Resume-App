package com.kuba.resumeapp.data.model

import android.content.Context
import com.kuba.resumeapp.R

data class LanguageInfo(
    val languageName: String?,
    val proficiency: Proficiency?
)

enum class Proficiency {
    NATIVE, BEGINNER, INTERMEDIATE, ADVANCED;

    fun toString(context: Context): String {
        return when (this) {
            NATIVE -> context.getString(R.string.lang_native)
            BEGINNER -> context.getString(R.string.lang_beginner)
            INTERMEDIATE -> context.getString(R.string.lang_intermediate)
            ADVANCED -> context.getString(R.string.lang_advanced)
        }
    }
}