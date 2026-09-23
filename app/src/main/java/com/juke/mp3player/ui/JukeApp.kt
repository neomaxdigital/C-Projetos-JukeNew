package com.juke.mp3player.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juke.mp3player.data.MockLibraryRepository
import com.juke.mp3player.ui.screens.LibraryScreen

object JukeRoutes {
    const val LIBRARY = "library"
    const val NOW_PLAYING = "now_playing"
    const val PLAYLISTS = "playlists"
    const val EQUALIZER = "equalizer"
    const val QUEUE = "queue"
    const val SETTINGS = "settings"
}

object ApprovedLayoutAssets {
    const val LIBRARY = "juke_layout_library"
    const val NOW_PLAYING = "juke_layout_now_playing"
    const val PLAYLISTS = "juke_layout_playlists"
    const val EQUALIZER = "juke_layout_equalizer"
    const val QUEUE = "juke_layout_queue"
    const val SETTINGS = "juke_layout_settings"
}

@Composable
fun JukeApp(onBack: () -> Unit) {
    val repository = remember { MockLibraryRepository() }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = JukeRoutes.LIBRARY) {
        composable(JukeRoutes.LIBRARY) {
            LibraryScreen(initialTracks = repository.tracks(), onBack = onBack)
        }
    }
}
