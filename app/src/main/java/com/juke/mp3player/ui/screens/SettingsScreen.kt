package com.juke.mp3player.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun SettingsScreen(onBack: () -> Unit) {
    var sound by remember { mutableStateOf(true) }
    var notifications by remember { mutableStateOf(true) }
    var resume by remember { mutableStateOf(true) }

    JukeScreen {
        Column(Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 14.dp).verticalScroll(rememberScrollState())) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = JukeWhite, modifier = Modifier.size(32.dp).clickable(onClick = onBack))
                Text("Configurações", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = 26.sp, modifier = Modifier.padding(start = 18.dp))
            }
            Spacer(Modifier.height(22.dp))
            SectionTitle("Reprodução")
            SettingSwitch("Som do aplicativo", "Ativar sons e feedbacks do player", sound) { sound = it }
            SettingSwitch("Retomar reprodução", "Continuar de onde parou", resume) { resume = it }
            SettingRow("Timer de dormir", "Desligado")
            Spacer(Modifier.height(18.dp))
            SectionTitle("Biblioteca")
            SettingRow("Adicionar músicas ou pastas", "Selecionar arquivos do dispositivo")
            SettingRow("Pastas da biblioteca", "Gerenciar acesso")
            SettingRow("Remover músicas do app", "Não apaga o arquivo físico")
            Spacer(Modifier.height(18.dp))
            SectionTitle("Áudio")
            SettingRow("Equalizador", "5 bandas e Subwoofer")
            SettingRow("Conversor WAV / MP3", "Ferramentas de áudio")
            Spacer(Modifier.height(18.dp))
            SectionTitle("Aplicativo")
            SettingSwitch("Notificações", "Controles de reprodução", notifications) { notifications = it }
            SettingRow("Tema", "Escuro")
            SettingRow("Sobre", "Juke Mp3 Player")
            SettingRow("Versão", "1.0.0")
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(title, color = JukeMuted, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 4.dp, bottom = 8.dp))
}

@Composable
private fun SettingRow(title: String, subtitle: String) {
    Row(Modifier.fillMaxWidth().padding(bottom = 9.dp).background(Color(0x6613191D), RoundedCornerShape(18.dp)).border(1.dp, Color(0xFF363D42), RoundedCornerShape(18.dp)).padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text(subtitle, color = JukeMuted, fontSize = 12.sp)
        }
        Text("›", color = JukeMuted, fontSize = 24.sp)
    }
}

@Composable
private fun SettingSwitch(title: String, subtitle: String, checked: Boolean, onChecked: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(bottom = 9.dp).background(Color(0x6613191D), RoundedCornerShape(18.dp)).border(1.dp, JukeOutline, RoundedCornerShape(18.dp)).padding(horizontal = 16.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, color = JukeWhite, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text(subtitle, color = JukeMuted, fontSize = 12.sp)
        }
        Switch(checked = checked, onCheckedChange = onChecked, colors = SwitchDefaults.colors(checkedThumbColor = JukeWhite, checkedTrackColor = Color(0xFF666D73), uncheckedThumbColor = Color(0xFFB0B4B8), uncheckedTrackColor = Color(0xFF31363A)))
    }
}
