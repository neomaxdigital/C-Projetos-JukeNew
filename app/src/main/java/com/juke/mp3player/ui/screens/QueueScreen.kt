package com.juke.mp3player.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juke.mp3player.music.Track
import com.juke.mp3player.ui.components.ApprovedArtwork
import com.juke.mp3player.ui.components.JukeScreen
import com.juke.mp3player.ui.theme.*

@Composable
fun QueueScreen(tracks: List<Track>, onBack: () -> Unit) {
    JukeScreen {
        Column(Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 14.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = JukeWhite, modifier = Modifier.size(32.dp).clickable(onClick = onBack))
                Text("Fila de reprodução", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier.weight(1f).padding(start = 18.dp))
                Text("Limpar", color = JukeMuted, fontSize = 14.sp)
            }
            Spacer(Modifier.size(24.dp))
            Text("Tocando agora", color = JukeMuted, fontSize = 13.sp)
            tracks.firstOrNull()?.let { QueueRow(it, true) }
            Spacer(Modifier.size(20.dp))
            Text("A seguir", color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Spacer(Modifier.size(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tracks.drop(1)) { track -> QueueRow(track, false) }
            }
        }
    }
}

@Composable
private fun QueueRow(track: Track, active: Boolean) {
    Row(Modifier.fillMaxWidth().background(if (active) Color(0xA1262D32) else Color(0x4013191D), RoundedCornerShape(18.dp)).border(1.dp, if (active) JukeOutline else Color(0xFF30373C), RoundedCornerShape(18.dp)).padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        ApprovedArtwork(track.artworkCrop, track.artworkFallback, Modifier.size(54.dp), 10f)
        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
            Text(track.title, color = JukeWhite, fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(track.artist + "  •  " + track.duration, color = JukeMuted, fontSize = 12.sp, maxLines = 1)
        }
        Icon(Icons.Default.DragHandle, "Reordenar", tint = JukeMuted, modifier = Modifier.size(24.dp))
        Icon(Icons.Default.MoreVert, "Mais opções", tint = JukeWhite, modifier = Modifier.size(25.dp))
    }
}
