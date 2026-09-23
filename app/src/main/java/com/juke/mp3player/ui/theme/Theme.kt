package com.juke.mp3player.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val JukeColors = darkColorScheme(
    primary = JukeWhite,
    onPrimary = JukeBlack,
    background = JukeBlack,
    onBackground = JukeWhite,
    surface = JukeGraphite,
    onSurface = JukeWhite,
    surfaceVariant = JukeRaised,
    onSurfaceVariant = JukeMuted,
    outline = JukeOutline,
    surfaceTint = Color.Transparent
)

@Composable
fun JukeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = JukeColors, content = content)
}
