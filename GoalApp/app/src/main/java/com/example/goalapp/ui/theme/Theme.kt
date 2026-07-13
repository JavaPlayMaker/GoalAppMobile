package com.example.goalapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AppBlue,
    onPrimary = AppWhite,
    secondary = AppBlue,
    onSecondary = AppWhite,
    tertiary = AppBlue,
    onTertiary = AppWhite,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = AppBlue,
    onSurfaceVariant = AppWhite,
    primaryContainer = AppBlue,
    onPrimaryContainer = AppWhite,
    secondaryContainer = AppBlue,
    onSecondaryContainer = AppWhite
)

private val LightColorScheme = lightColorScheme(
    primary = AppBlue,
    onPrimary = AppWhite,
    secondary = AppBlue,
    onSecondary = AppWhite,
    tertiary = AppBlue,
    onTertiary = AppWhite,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = AppBlue,
    onSurfaceVariant = AppWhite,
    primaryContainer = AppBlue,
    onPrimaryContainer = AppWhite,
    secondaryContainer = AppBlue,
    onSecondaryContainer = AppWhite
)

@Composable
fun GoalAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    // Ignoring dynamicColor to maintain strict design system as requested

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
