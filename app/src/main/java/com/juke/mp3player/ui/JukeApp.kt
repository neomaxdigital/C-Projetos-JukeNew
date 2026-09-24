package com.juke.mp3player.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juke.mp3player.data.MockLibraryRepository
import com.juke.mp3player.ui.screens.*

object JukeRoutes {
    const val LIBRARY = "library"
    const val NOW_PLAYING = "now_playing"
    const val PLAYLISTS = "playlists"
    const val EQUALIZER = "equalizer"
    const val QUEUE = "queue"
    const val SETTINGS = "settings"
}

object ApprovedLayoutAssets {
    const val LIBRARY = "reference_library_final"
    const val NOW_PLAYING = "reference_now_playing_final"
    const val PLAYLISTS = "reference_playlists_final"
    const val EQUALIZER = "reference_equalizer_final"
    const val QUEUE = "reference_queue_final"
    const val SETTINGS = "reference_settings_final"
}

@Composable
fun JukeApp(onBack: () -> Unit) {
    val repository = remember { MockLibraryRepository() }
    val tracks = remember { repository.tracks() }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = JukeRoutes.LIBRARY) {
        composable(JukeRoutes.LIBRARY) {
            LibraryScreen(
                initialTracks = tracks,
                onBack = onBack,
                onNowPlaying = { navController.navigate(JukeRoutes.NOW_PLAYING) },
                onPlaylists = { navController.navigate(JukeRoutes.PLAYLISTS) },
                onEqualizer = { navController.navigate(JukeRoutes.EQUALIZER) },
                onQueue = { navController.navigate(JukeRoutes.QUEUE) },
                onSettings = { navController.navigate(JukeRoutes.SETTINGS) }
            )
        }
        composable(JukeRoutes.NOW_PLAYING) {
            NowPlayingScreen(tracks.first(), { navController.popBackStack() }, { navController.navigate(JukeRoutes.EQUALIZER) }, { navController.navigate(JukeRoutes.QUEUE) })
        }
        composable(JukeRoutes.PLAYLISTS) { PlaylistsScreen { navController.popBackStack() } }
        composable(JukeRoutes.EQUALIZER) { EqualizerScreen { navController.popBackStack() } }
        composable(JukeRoutes.QUEUE) { QueueScreen(tracks) { navController.popBackStack() } }
        composable(JukeRoutes.SETTINGS) { SettingsScreen { navController.popBackStack() } }
    }
}
