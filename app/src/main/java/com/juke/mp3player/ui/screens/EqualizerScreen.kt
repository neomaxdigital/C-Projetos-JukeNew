package com.juke.mp3player.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juke.mp3player.ui.components.JukeScreen
import com.juke.mp3player.ui.theme.*

@Composable
fun EqualizerScreen(onBack: () -> Unit) {
    var eqEnabled by remember { mutableStateOf(true) }
    var subEnabled by remember { mutableStateOf(true) }
    var preset by remember { mutableStateOf("Flat") }
    var sub by remember { mutableFloatStateOf(.5f) }
    val bandValues = remember { mutableStateListOf(.58f, .42f, .55f, .68f, .54f) }
    val bands = listOf("60 Hz" to "Graves", "230 Hz" to "Baixos", "910 Hz" to "Médios", "3.6 kHz" to "Agudos", "14 kHz" to "Brilho")

    JukeScreen {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 14.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = JukeWhite, modifier = Modifier.size(32.dp).clickable(onClick = onBack))
                Text("Equalizador", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 26.sp, modifier = Modifier.weight(1f).padding(start = 18.dp))
                GraySwitch(eqEnabled) { eqEnabled = it }
            }
            Spacer(Modifier.height(18.dp))
            Text("Predefinições", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 19.sp)
            Spacer(Modifier.height(9.dp))
            listOf("Flat", "Pop", "Rock", "Clássico", "Jazz", "Hip-hop", "Dance", "Vocal", "Heavy Metal").chunked(3).forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { name ->
                        Box(Modifier.weight(1f).background(if (preset == name) Color(0xFF666D73) else Color(0x55171C20), RoundedCornerShape(22.dp)).border(1.dp, if (preset == name) Color(0xFFAEB3B7) else JukeOutline, RoundedCornerShape(22.dp)).clickable(enabled = eqEnabled) { preset = name }.padding(vertical = 10.dp), contentAlignment = Alignment.Center) {
                            Text(name, color = if (eqEnabled) JukeWhite else JukeMuted.copy(alpha = .45f), fontSize = 13.sp)
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
            Spacer(Modifier.height(8.dp))
            Column(Modifier.fillMaxWidth().background(Color(0x5513191D), RoundedCornerShape(20.dp)).border(1.dp, JukeOutline, RoundedCornerShape(20.dp)).padding(14.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Ajuste manual", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 19.sp, modifier = Modifier.weight(1f))
                    Icon(Icons.Default.Refresh, "Redefinir", tint = JukeWhite, modifier = Modifier.size(22.dp).clickable { for (i in bandValues.indices) bandValues[i] = .5f })
                    Text("Redefinir", color = JukeMuted, fontSize = 12.sp, modifier = Modifier.padding(start = 5.dp))
                }
                Spacer(Modifier.height(8.dp))
                bands.forEachIndexed { i, pair ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.width(72.dp)) {
                            Text(pair.first, color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            Text(pair.second, color = JukeMuted, fontSize = 10.sp)
                        }
                        Slider(value = bandValues[i], onValueChange = { if (eqEnabled) bandValues[i] = it }, modifier = Modifier.weight(1f), colors = SliderDefaults.colors(thumbColor = if (eqEnabled) JukeWhite else JukeMuted, activeTrackColor = if (eqEnabled) JukeWhite else Color(0xFF4B5156), inactiveTrackColor = Color(0xFF56606A)))
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Column(Modifier.fillMaxWidth().background(Color(0x5513191D), RoundedCornerShape(20.dp)).border(1.dp, JukeOutline, RoundedCornerShape(20.dp)).padding(14.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Subwoofer", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 19.sp)
                        Text("Mais profundidade para a sua música.", color = JukeMuted, fontSize = 12.sp)
                    }
                    GraySwitch(subEnabled) { subEnabled = it }
                }
                Slider(value = sub, onValueChange = { if (subEnabled) sub = it }, colors = SliderDefaults.colors(thumbColor = if (subEnabled) JukeWhite else JukeMuted, activeTrackColor = if (subEnabled) JukeWhite else Color(0xFF4B5156), inactiveTrackColor = Color(0xFF56606A)))
                Text("+" + (sub * 60).toInt(), color = if (subEnabled) JukeWhite else JukeMuted, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.align(Alignment.End))
            }
        }
    }
}

@Composable
private fun GraySwitch(checked: Boolean, onChange: (Boolean) -> Unit) {
    Switch(checked = checked, onCheckedChange = onChange, colors = SwitchDefaults.colors(checkedThumbColor = JukeWhite, checkedTrackColor = Color(0xFF666D73), uncheckedThumbColor = Color(0xFFB0B4B8), uncheckedTrackColor = Color(0xFF31363A)))
}
