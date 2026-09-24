package com.juke.mp3player.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juke.mp3player.R
import com.juke.mp3player.ui.components.ReferenceScreen

private fun Modifier.eqHotspot(x: Float, y: Float, w: Float, h: Float, sw: Float, sh: Float) =
    this.offset((sw * x / 1080f).dp, (sh * y / 2400f).dp)
        .size((sw * w / 1080f).dp, (sh * h / 2400f).dp)

@Composable
fun EqualizerScreen(onBack: () -> Unit) {
    var eqEnabled by remember { mutableStateOf(true) }
    var subEnabled by remember { mutableStateOf(true) }

    ReferenceScreen(R.drawable.reference_equalizer_final) { sw, sh ->
        Box(Modifier.eqHotspot(20f, 20f, 120f, 140f, sw, sh).clickable(onClick = onBack))

        Switch(
            checked = eqEnabled,
            onCheckedChange = { eqEnabled = it },
            modifier = Modifier.eqHotspot(890f, 95f, 130f, 90f, sw, sh),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF666D73),
                uncheckedThumbColor = Color(0xFFB0B4B8),
                uncheckedTrackColor = Color(0xFF31363A)
            )
        )

        Switch(
            checked = subEnabled,
            onCheckedChange = { subEnabled = it },
            modifier = Modifier.eqHotspot(875f, 1800f, 145f, 90f, sw, sh),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF666D73),
                uncheckedThumbColor = Color(0xFFB0B4B8),
                uncheckedTrackColor = Color(0xFF31363A)
            )
        )
    }
}
