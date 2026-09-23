package com.juke.mp3player.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.juke.mp3player.R
import com.juke.mp3player.music.ArtworkCrop
import kotlin.math.roundToInt

@Composable
fun ApprovedArtwork(
    crop: ArtworkCrop?,
    modifier: Modifier = Modifier,
    cornerRadius: Float = 16f
) {
    val approvedLayout = ImageBitmap.imageResource(R.drawable.juke_layout_library)
    val shape = remember(cornerRadius) { RoundedCornerShape(cornerRadius.dp) }

    Canvas(
        modifier
            .clip(shape)
            .background(Color(0xFF252A2E))
    ) {
        crop ?: return@Canvas
        drawImage(
            image = approvedLayout,
            srcOffset = IntOffset(crop.left, crop.top),
            srcSize = IntSize(crop.width, crop.height),
            dstOffset = IntOffset.Zero,
            dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
        )
    }
}
