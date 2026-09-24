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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juke.mp3player.ui.components.JukeScreen
import com.juke.mp3player.ui.theme.*

private data class PlaylistItem(val title: String, val subtitle: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun PlaylistsScreen(onBack: () -> Unit) {
    val smart = listOf(
        PlaylistItem("Favoritos", "Suas músicas curtidas", Icons.Default.Favorite),
        PlaylistItem("Última adição", "Adicionadas recentemente", Icons.Default.LibraryMusic),
        PlaylistItem("Reproduções recentes", "O que você ouviu por último", Icons.Default.History),
        PlaylistItem("Mais reproduzidas", "As suas mais tocadas", Icons.Default.TrendingUp)
    )
    val mine = listOf(
        PlaylistItem("Minha Playlist", "18 músicas", Icons.Default.MusicNote),
        PlaylistItem("Viagem", "26 músicas", Icons.Default.MusicNote)
    )

    JukeScreen {
        Column(Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 14.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = JukeWhite, modifier = Modifier.size(32.dp).clickable(onClick = onBack))
                Text("Playlists", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 27.sp, modifier = Modifier.weight(1f).padding(start = 18.dp))
                Box(Modifier.size(38.dp).background(Color(0x55171C20), RoundedCornerShape(12.dp)).border(1.dp, JukeOutline, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Add, "Nova playlist", tint = JukeWhite)
                }
            }
            Spacer(Modifier.size(24.dp))
            Text("Playlists inteligentes", color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 19.sp)
            Spacer(Modifier.size(10.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(smart) { item -> PlaylistRow(item) }
                item { Text("Minhas playlists", color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 19.sp, modifier = Modifier.padding(top = 22.dp, bottom = 10.dp)) }
                items(mine) { item -> PlaylistRow(item) }
                item {
                    Row(Modifier.fillMaxWidth().padding(top = 8.dp).background(Color(0x55171C20), RoundedCornerShape(18.dp)).border(1.dp, JukeOutline, RoundedCornerShape(18.dp)).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Add, null, tint = JukeWhite)
                        Text("Nova playlist", color = JukeWhite, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 12.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaylistRow(item: PlaylistItem) {
    Row(Modifier.fillMaxWidth().background(Color(0x6613191D), RoundedCornerShape(20.dp)).border(1.dp, Color(0xFF3A4248), RoundedCornerShape(20.dp)).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(58.dp).background(Color(0xFF30363B), RoundedCornerShape(16.dp)), contentAlignment = Alignment.Center) {
            Icon(item.icon, null, tint = JukeWhite, modifier = Modifier.size(28.dp))
        }
        Column(Modifier.padding(start = 14.dp)) {
            Text(item.title, color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Text(item.subtitle, color = JukeMuted, fontSize = 13.sp)
        }
    }
}
