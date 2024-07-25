package com.example.tvpresentation

import androidx.compose.ui.graphics.Color

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