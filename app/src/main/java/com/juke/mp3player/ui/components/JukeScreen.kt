package com.juke.mp3player.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juke.mp3player.ui.theme.JukeBlack

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JukeScreen(content: @Composable BoxScope.() -> Unit) {
    val top = WindowInsets.statusBarsIgnoringVisibility.asPaddingValues().calculateTopPadding()
    val bottom = WindowInsets.navigationBarsIgnoringVisibility.asPaddingValues().calculateBottomPadding()
    Box(Modifier.fillMaxSize().background(JukeBlack)) {
        JukeAppBackground(Modifier.fillMaxSize())
        Box(Modifier.fillMaxSize().padding(top = top, bottom = bottom), content = content)
    }
}
