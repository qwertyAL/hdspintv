package com.example.hdspintv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

data class Palette(
    val primary: Color,
    val background: Color,
    val backgroundCard: Color,
    val textPrimary: Color,
    val textSecondary: Color
)

val palette = Palette(
    primary = Color(0xFF00BAFC),
    background = Color(0xFF1a191f),
    backgroundCard = Color(0xFF111113),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFb0b0c9)
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HDSpinTVTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
//    val colorScheme = if (isInDarkTheme) {
//        darkColorScheme(
//            primary = Purple80,
//            secondary = PurpleGrey80,
//            tertiary = Pink80
//        )
//    } else {
//        lightColorScheme(
//            primary = Purple40,
//            secondary = PurpleGrey40,
//            tertiary = Pink40
//        )
//    }
    val colorScheme = darkColorScheme()
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}