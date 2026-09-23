package com.juke.mp3player.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.juke.mp3player.music.Track
import com.juke.mp3player.ui.components.ApprovedArtwork
import com.juke.mp3player.ui.theme.JukeBlack
import com.juke.mp3player.ui.theme.JukeGraphite
import com.juke.mp3player.ui.theme.JukeMuted
import com.juke.mp3player.ui.theme.JukeOutline
import com.juke.mp3player.ui.theme.JukeWhite

private const val DESIGN_WIDTH = 1080f
private const val DESIGN_HEIGHT = 2400f

private enum class LibraryTab(val label: String) {
    TRACKS("Faixas"), ARTISTS("Artistas"), ALBUMS("Álbuns"), PLAYLISTS("Playlists"), FOLDERS("Pastas")
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LibraryScreen(initialTracks: List<Track>, onBack: () -> Unit) {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(LibraryTab.TRACKS) }
    var descending by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var playing by remember { mutableStateOf(true) }
    var topMenuOpen by remember { mutableStateOf(false) }
    var trackMenuId by remember { mutableStateOf<Long?>(null) }

    val visibleTracks = remember(initialTracks, query, descending) {
        initialTracks
            .filter { query.isBlank() || it.title.contains(query, true) || it.artist.contains(query, true) }
            .let { if (descending) it.reversed() else it }
    }
    val currentTrack = initialTracks.getOrNull(currentIndex)

    BoxWithConstraints(Modifier.fillMaxSize().background(JukeBlack)) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val statusBarInset = WindowInsets.statusBarsIgnoringVisibility.asPaddingValues().calculateTopPadding()
        val navigationBarInset = WindowInsets.navigationBarsIgnoringVisibility.asPaddingValues().calculateBottomPadding()
        val safeHeight = (screenHeight - statusBarInset - navigationBarInset).coerceAtLeast(1.dp)

        JukeCurvedBackground()

        Box(
            Modifier
                .offset(y = statusBarInset)
                .size(width = screenWidth, height = safeHeight)
        ) {
            val xScale = screenWidth.value / DESIGN_WIDTH
            val yScale = safeHeight.value / DESIGN_HEIGHT
            val scale = xScale
            fun ux(value: Float): Dp = (value * xScale).dp
            fun uy(value: Float): Dp = (value * yScale).dp

            val miniPlayerBottom = maxOf(uy(39f), 10.dp)
            val miniPlayerHeight = maxOf(uy(157f), 72.dp)
            val miniPlayerSide = maxOf(ux(48f), 16.dp)
            val miniPlayerTop = safeHeight - miniPlayerBottom - miniPlayerHeight

            Header(
            scale = scale,
            onBack = onBack,
            menuOpen = topMenuOpen,
            onMenu = { topMenuOpen = true },
            onDismissMenu = { topMenuOpen = false }
        )
        SearchField(
            query = query,
            onQuery = { query = it },
            scale = scale,
            modifier = Modifier.offset(ux(86f), uy(205f)).size(ux(934f), uy(90f))
        )
        LibraryTabs(
            selected = selectedTab,
            onSelected = { selectedTab = it },
            scale = scale,
            modifier = Modifier.offset(ux(86f), uy(328f)).size(ux(934f), uy(76f))
        )

        if (selectedTab == LibraryTab.TRACKS) {
            TrackHeader(
                count = if (query.isBlank() && initialTracks.size == 12) 328 else visibleTracks.size,
                scale = scale,
                onSort = { descending = !descending },
                modifier = Modifier.offset(ux(86f), uy(465f)).size(ux(921f), uy(62f))
            )
            val listTop = uy(535f)
            LazyColumn(
                modifier = Modifier
                    .offset(x = ux(86f), y = listTop)
                    .size(width = ux(921f), height = (miniPlayerTop - listTop - 8.dp).coerceAtLeast(0.dp)),
                contentPadding = PaddingValues(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(uy(10f))
            ) {
                items(visibleTracks, key = { it.id }) { track ->
                    TrackRow(
                        track = track,
                        selected = currentTrack?.id == track.id,
                        menuOpen = trackMenuId == track.id,
                        onMenu = { trackMenuId = track.id },
                        onDismissMenu = { trackMenuId = null },
                        onClick = {
                            currentIndex = initialTracks.indexOfFirst { it.id == track.id }.coerceAtLeast(0)
                            playing = true
                        },
                        scale = scale
                    )
                }
            }
        } else {
            Text(
                text = "${selectedTab.label} preparada para integração",
                color = JukeMuted,
                fontSize = (29f * scale).sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        currentTrack?.let { track ->
            MiniPlayer(
                track = track,
                playing = playing,
                scale = scale,
                onPrevious = { currentIndex = if (currentIndex <= 0) initialTracks.lastIndex else currentIndex - 1 },
                onToggle = { playing = !playing },
                onNext = { currentIndex = if (currentIndex >= initialTracks.lastIndex) 0 else currentIndex + 1 },
                modifier = Modifier
                    .offset(x = miniPlayerSide, y = miniPlayerTop)
                    .size(width = screenWidth - miniPlayerSide * 2, height = miniPlayerHeight)
                    .zIndex(2f)
            )
        }
        }
    }
}

@Composable
private fun JukeCurvedBackground() {
    Canvas(Modifier.fillMaxSize()) {
        drawRect(Color(0xFF090D10))
        // Soft upper ribbons: intentionally broad and low-contrast so the
        // branding sits on a calm surface instead of inside a dark oval.
        drawPath(Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width * .34f, 0f)
            cubicTo(
                size.width * .30f, size.height * .035f,
                size.width * .18f, size.height * .070f,
                0f, size.height * .115f
            )
            close()
        }, Color(0xFF12171B))
        drawPath(Path().apply {
            moveTo(size.width * .50f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height * .105f)
            cubicTo(
                size.width * .83f, size.height * .125f,
                size.width * .67f, size.height * .115f,
                size.width * .52f, size.height * .085f
            )
            cubicTo(
                size.width * .46f, size.height * .065f,
                size.width * .45f, size.height * .028f,
                size.width * .50f, 0f
            )
            close()
        }, Color(0xFF0C1114))
        drawPath(Path().apply {
            moveTo(0f, size.height * .35f)
            cubicTo(size.width * .26f, size.height * .21f, size.width * .62f, size.height * .28f, size.width, size.height * .17f)
            lineTo(size.width, size.height * .31f)
            cubicTo(size.width * .72f, size.height * .47f, size.width * .28f, size.height * .42f, 0f, size.height * .54f)
            close()
        }, Color(0xFF0B1013))
        drawPath(Path().apply {
            moveTo(0f, size.height * .58f)
            cubicTo(size.width * .24f, size.height * .49f, size.width * .56f, size.height * .61f, size.width, size.height * .46f)
            lineTo(size.width, size.height * .72f)
            cubicTo(size.width * .72f, size.height * .82f, size.width * .31f, size.height * .70f, 0f, size.height * .79f)
            close()
        }, Color(0xFF11161A))
        drawPath(Path().apply {
            moveTo(0f, size.height * .86f)
            cubicTo(size.width * .27f, size.height * .74f, size.width * .64f, size.height * .91f, size.width, size.height * .77f)
            lineTo(size.width, size.height); lineTo(0f, size.height); close()
        }, Color(0xFF0B1013))
    }
}

@Composable
private fun Header(scale: Float, onBack: () -> Unit, menuOpen: Boolean, onMenu: () -> Unit, onDismissMenu: () -> Unit) {
    fun u(value: Float) = (value * scale).dp
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier.offset(u(72f), u(60f)).size(u(72f)).semantics { contentDescription = "Voltar" }.clickable(onClick = onBack),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = JukeWhite, modifier = Modifier.size(u(55f)))
        }
        Row(
            Modifier
                .align(Alignment.TopCenter)
                .offset(y = u(42f))
                .height(u(102f)),
            verticalAlignment = Alignment.Top
        ) {
            WaveformLogo(scale)
            Box(Modifier.padding(start = u(22f))) {
                Text("Juke", color = JukeWhite, fontWeight = FontWeight.Bold, fontSize = u(62f).value.sp)
                Text("Mp3 Player", color = JukeMuted, fontSize = u(30f).value.sp, modifier = Modifier.offset(y = u(67f)))
            }
        }
        Box(
            Modifier.offset(u(948f), u(60f)).size(u(65f)).semantics { contentDescription = "Mais opções" }.clickable(onClick = onMenu),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.MoreVert, null, tint = JukeWhite, modifier = Modifier.size(u(46f)))
            DropdownMenu(expanded = menuOpen, onDismissRequest = onDismissMenu, containerColor = JukeGraphite) {
                DropdownMenuItem(text = { Text("Fila de reprodução") }, onClick = onDismissMenu)
                DropdownMenuItem(text = { Text("Equalizador") }, onClick = onDismissMenu)
                DropdownMenuItem(text = { Text("Configurações") }, onClick = onDismissMenu)
            }
        }
    }
}

@Composable
private fun WaveformLogo(scale: Float) {
    Canvas(Modifier.size((70f * scale).dp, (82f * scale).dp)) {
        listOf(8f, 23f, 38f, 53f, 68f).zip(listOf(34f, 60f, 82f, 60f, 34f)).forEach { (x, height) ->
            drawLine(
                color = Color(0xFFD0D3D6),
                start = Offset(size.width * x / 70f, size.height * (82f - height) / 164f),
                end = Offset(size.width * x / 70f, size.height * (82f + height) / 164f),
                strokeWidth = size.width * 8f / 70f,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
private fun SearchField(query: String, onQuery: (String) -> Unit, scale: Float, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape((38f * scale).dp)
    Row(
        modifier.clip(shape).background(Color(0x4D171C20)).border((1.5f * scale).dp, JukeOutline, shape)
            .padding(horizontal = (28f * scale).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, null, tint = JukeWhite, modifier = Modifier.size((39f * scale).dp))
        BasicTextField(
            value = query,
            onValueChange = onQuery,
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(color = JukeWhite, fontSize = (31f * scale).sp),
            modifier = Modifier.padding(start = (36f * scale).dp).weight(1f),
            decorationBox = { field ->
                if (query.isBlank()) Text("Buscar músicas, artistas, álbuns...", color = JukeMuted, fontSize = (31f * scale).sp)
                field()
            }
        )
    }
}

@Composable
private fun LibraryTabs(selected: LibraryTab, onSelected: (LibraryTab) -> Unit, scale: Float, modifier: Modifier = Modifier) {
    val widths = listOf(187f, 181f, 167f, 181f, 166f)
    Row(modifier, horizontalArrangement = Arrangement.spacedBy((12f * scale).dp)) {
        LibraryTab.entries.forEachIndexed { index, tab ->
            val active = tab == selected
            val shape = RoundedCornerShape((17f * scale).dp)
            Box(
                Modifier.size(width = (widths[index] * scale).dp, height = (64f * scale).dp).clip(shape)
                    .background(if (active) Color(0xFF3C4247) else Color(0x57171C20))
                    .border((1.5f * scale).dp, if (active) Color(0xFF969CA1) else Color(0xFF32383D), shape)
                    .clickable { onSelected(tab) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    tab.label,
                    color = if (active) JukeWhite else JukeMuted,
                    fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal,
                    fontSize = (29f * scale).sp
                )
            }
        }
    }
}

@Composable
private fun TrackHeader(count: Int, scale: Float, onSort: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier) {
        Text(
            buildAnnotatedString {
                append("Todas as faixas ")
                withStyle(SpanStyle(color = JukeMuted, fontWeight = FontWeight.Normal)) { append("($count)") }
            },
            color = JukeWhite,
            fontSize = (39f * scale).sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Box(
            Modifier.align(Alignment.CenterEnd).size((62f * scale).dp).semantics { contentDescription = "Ordenar faixas" }
                .clickable(onClick = onSort),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.AutoMirrored.Filled.Sort, null, tint = JukeWhite, modifier = Modifier.size((43f * scale).dp))
        }
    }
}

@Composable
private fun TrackRow(
    track: Track,
    selected: Boolean,
    menuOpen: Boolean,
    onMenu: () -> Unit,
    onDismissMenu: () -> Unit,
    onClick: () -> Unit,
    scale: Float
) {
    Box(Modifier.size(width = (921f * scale).dp, height = (118f * scale).dp).clickable(onClick = onClick)) {
        ApprovedArtwork(crop = track.artworkCrop, fallback = track.artworkFallback, cornerRadius = 14f * scale, modifier = Modifier.size((118f * scale).dp))
        Text(
            track.title,
            color = JukeWhite,
            fontSize = (38f * scale).sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.offset((197f * scale).dp, (10f * scale).dp).width((610f * scale).dp)
        )
        Text(
            "${track.artist}  •  ${track.duration}",
            color = JukeMuted,
            fontSize = (33f * scale).sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.offset((197f * scale).dp, (67f * scale).dp).width((610f * scale).dp)
        )
        Box(
            Modifier.align(Alignment.CenterEnd).size((62f * scale).dp).semantics { contentDescription = "Opções de ${track.title}" }
                .clickable(onClick = onMenu),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.MoreVert, null, tint = JukeWhite, modifier = Modifier.size((39f * scale).dp))
            DropdownMenu(expanded = menuOpen, onDismissRequest = onDismissMenu, containerColor = JukeGraphite) {
                DropdownMenuItem(text = { Text("Favoritar") }, onClick = onDismissMenu)
                DropdownMenuItem(text = { Text("Tocar a seguir") }, onClick = onDismissMenu)
                DropdownMenuItem(text = { Text("Adicionar à playlist") }, onClick = onDismissMenu)
            }
        }
    }
}

@Composable
private fun MiniPlayer(
    track: Track,
    playing: Boolean,
    scale: Float,
    onPrevious: () -> Unit,
    onToggle: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(maxOf((38f * scale).dp, 18.dp))
    Row(
        modifier
            .clip(shape)
            .background(Color(0xE612171A))
            .border(maxOf((1.5f * scale).dp, 1.dp), Color(0xFF5B6268), shape)
            .padding(start = 12.dp, end = 12.dp, top = 9.dp, bottom = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ApprovedArtwork(
            crop = track.artworkCrop,
            fallback = track.artworkFallback,
            cornerRadius = 9f,
            modifier = Modifier.size(46.dp)
        )
        Column(
            Modifier
                .padding(start = 10.dp, end = 6.dp)
                .weight(1f)
        ) {
            Text(
                track.title,
                color = JukeWhite,
                fontSize = maxOf(12f, 30f * scale).sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                track.artist,
                color = JukeMuted,
                fontSize = maxOf(10.5f, 26f * scale).sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier.width(126.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MiniPlayerButton(Icons.Default.SkipPrevious, "Anterior", 38.dp, 24.dp, onPrevious)
            MiniPlayerButton(
                if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
                if (playing) "Pausar" else "Tocar",
                42.dp,
                27.dp,
                onToggle
            )
            MiniPlayerButton(Icons.Default.SkipNext, "Próxima", 38.dp, 24.dp, onNext)
        }
    }
}

@Composable
private fun MiniPlayerButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    touchSize: Dp,
    iconSize: Dp,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .size(touchSize)
            .semantics { contentDescription = label }
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, tint = JukeWhite, modifier = Modifier.size(iconSize))
    }
}
