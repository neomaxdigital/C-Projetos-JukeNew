package com.juke.mp3player.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juke.mp3player.R
import com.juke.mp3player.ui.components.ReferenceScreen

private fun Modifier.sHotspot(x: Float, y: Float, w: Float, h: Float, sw: Float, sh: Float) =
    this.offset((sw * x / 1080f).dp, (sh * y / 2400f).dp)
        .size((sw * w / 1080f).dp, (sh * h / 2400f).dp)

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    ReferenceScreen(R.drawable.reference_settings_final) { sw, sh ->
        Box(Modifier.sHotspot(20f, 20f, 120f, 140f, sw, sh).clickable(onClick = onBack))
    }
}
