package com.juke.mp3player.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juke.mp3player.music.Track
import com.juke.mp3player.ui.components.ApprovedArtwork
import com.juke.mp3player.ui.components.JukeScreen
import com.juke.mp3player.ui.theme.*

@Composable
fun NowPlayingScreen(track: Track, onBack: () -> Unit, onEqualizer: () -> Unit, onQueue: () -> Unit) {
    var playing by remember { mutableStateOf(true) }
    var progress by remember { mutableFloatStateOf(.34f) }
    var tab by remember { mutableStateOf("Música") }

    JukeScreen {
        Column(Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = JukeWhite, modifier = Modifier.size(32.dp).clickable(onClick = onBack))
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Juke", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    Text("Mp3 Player", color = JukeMuted, fontSize = 11.sp)
                }
                Icon(Icons.Default.MoreVert, "Mais opções", tint = JukeWhite, modifier = Modifier.size(30.dp).clickable(onClick = onQueue))
            }
            Spacer(Modifier.height(18.dp))
            Row(Modifier.background(Color(0x55171C20), RoundedCornerShape(18.dp)).border(1.dp, JukeOutline, RoundedCornerShape(18.dp)).padding(4.dp)) {
                listOf("Música", "Letra").forEach { item ->
                    Box(Modifier.background(if (tab == item) Color(0xFF3B4146) else Color.Transparent, RoundedCornerShape(14.dp)).clickable { tab = item }.padding(horizontal = 34.dp, vertical = 9.dp)) {
                        Text(item, color = if (tab == item) JukeWhite else JukeMuted, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            ApprovedArtwork(track.artworkCrop, track.artworkFallback, Modifier.fillMaxWidth().height(340.dp), 22f)
            Spacer(Modifier.height(18.dp))
            Text(track.title, color = JukeWhite, fontSize = 26.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(track.artist, color = JukeMuted, fontSize = 16.sp)
            Text("Álbum", color = JukeMuted.copy(alpha = .75f), fontSize = 13.sp)
            Spacer(Modifier.height(18.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                PlayerAction(Icons.Default.FavoriteBorder, "Curtir") {}
                PlayerAction(Icons.Default.Equalizer, "Equalizador", onEqualizer)
                PlayerAction(Icons.Default.Share, "Compartilhar") {}
                PlayerAction(Icons.Default.PlaylistAdd, "Adicionar") {}
            }
            Spacer(Modifier.height(18.dp))
            Slider(value = progress, onValueChange = { progress = it }, colors = SliderDefaults.colors(thumbColor = JukeWhite, activeTrackColor = JukeWhite, inactiveTrackColor = Color(0xFF56606A)))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("1:28", color = JukeMuted, fontSize = 12.sp)
                Text(track.duration, color = JukeMuted, fontSize = 12.sp)
            }
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Shuffle, "Aleatório", tint = JukeMuted, modifier = Modifier.size(26.dp))
                Icon(Icons.Default.SkipPrevious, "Anterior", tint = JukeWhite, modifier = Modifier.size(38.dp))
                Box(Modifier.size(64.dp).background(JukeWhite, CircleShape).clickable { playing = !playing }, contentAlignment = Alignment.Center) {
                    Icon(if (playing) Icons.Default.Pause else Icons.Default.PlayArrow, if (playing) "Pausar" else "Tocar", tint = JukeGraphite, modifier = Modifier.size(34.dp))
                }
                Icon(Icons.Default.SkipNext, "Próxima", tint = JukeWhite, modifier = Modifier.size(38.dp))
                Icon(Icons.Default.Repeat, "Repetir", tint = JukeMuted, modifier = Modifier.size(26.dp))
            }
        }
    }
}

@Composable
private fun PlayerAction(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable(onClick = onClick)) {
        Box(Modifier.size(48.dp).background(Color(0x99171C20), CircleShape).border(1.dp, JukeOutline, CircleShape), contentAlignment = Alignment.Center) {
            Icon(icon, label, tint = JukeWhite, modifier = Modifier.size(24.dp))
        }
        Text(label, color = JukeMuted, fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 6.dp))
    }
}
