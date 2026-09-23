package com.juke.mp3player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.juke.mp3player.ui.JukeApp
import com.juke.mp3player.ui.theme.JukeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Draw behind the bars so the app background remains continuous, but
        // keep both system bars visible. Screen composables consume the real
        // status/navigation insets and keep interactive content inside safe areas.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        setContent {
            JukeTheme {
                JukeApp(onBack = ::finish)
            }
        }
    }
}
