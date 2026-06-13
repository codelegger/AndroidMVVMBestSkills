package com.codelegger.androidmvvmbestskills.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = md_primary_light,
    secondary = md_secondary_light,
    tertiary = md_tertiary_light,
    background = md_background_light,
    surface = md_surface_light,
)

private val DarkColorScheme = darkColorScheme(
    primary = md_primary_dark,
    secondary = md_secondary_dark,
    tertiary = md_tertiary_dark,
    background = md_background_dark,
    surface = md_surface_dark,
)

/**
 * App-wide Material 3 theme. Follows the system light/dark setting by default and
 * opts into Material You dynamic color on Android 12+ when available.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
    )
}
