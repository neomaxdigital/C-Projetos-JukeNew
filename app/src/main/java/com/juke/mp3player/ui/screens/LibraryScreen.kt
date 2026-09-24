package com.juke.mp3player.ui.screens

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.juke.mp3player.R
import com.juke.mp3player.music.Track
import com.juke.mp3player.ui.components.ReferenceScreen

private fun Modifier.hotspot(x: Float, y: Float, w: Float, h: Float, screenW: Float, screenH: Float) =
    this.offset((screenW * x / 1080f).dp, (screenH * y / 2400f).dp)
        .size((screenW * w / 1080f).dp, (screenH * h / 2400f).dp)

@Composable
fun LibraryScreen(
    initialTracks: List<Track>,
    onBack: () -> Unit,
    onNowPlaying: () -> Unit,
    onPlaylists: () -> Unit,
    onEqualizer: () -> Unit,
    onQueue: () -> Unit,
    onSettings: () -> Unit
) {
    var menuOpen by remember { mutableStateOf(false) }
    val view = LocalView.current

    DisposableEffect(view) {
        val window = (view.context as? Activity)?.window
        val previousNavigationBarColor = window?.navigationBarColor
        val previousStatusBarColor = window?.statusBarColor
        val previousNavigationBarContrast = window?.isNavigationBarContrastEnforced

        window?.navigationBarColor = Color.Transparent.toArgb()
        window?.statusBarColor = Color.Transparent.toArgb()
        window?.isNavigationBarContrastEnforced = false

        onDispose {
            previousNavigationBarColor?.let { window.navigationBarColor = it }
            previousStatusBarColor?.let { window.statusBarColor = it }
            previousNavigationBarContrast?.let { window.isNavigationBarContrastEnforced = it }
        }
    }

    ReferenceScreen(R.drawable.reference_library_final) { sw, sh ->
        Box(Modifier.hotspot(20f, 25f, 110f, 130f, sw, sh).clickable(onClick = onBack))
        Box(Modifier.hotspot(925f, 30f, 120f, 130f, sw, sh).clickable { menuOpen = true }) {
            DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                DropdownMenuItem(text = { Text("Fila de reprodução") }, onClick = { menuOpen = false; onQueue() })
                DropdownMenuItem(text = { Text("Equalizador") }, onClick = { menuOpen = false; onEqualizer() })
                DropdownMenuItem(text = { Text("Configurações") }, onClick = { menuOpen = false; onSettings() })
            }
        }
        Box(Modifier.hotspot(650f, 290f, 220f, 120f, sw, sh).clickable(onClick = onPlaylists))
        Box(Modifier.hotspot(35f, 2140f, 1010f, 210f, sw, sh).clickable(onClick = onNowPlaying))
    }
}
