package com.juke.mp3player.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.juke.mp3player.R
import com.juke.mp3player.music.ArtworkCrop
import com.juke.mp3player.music.ArtworkFallback
import kotlin.math.roundToInt

@Composable
fun ApprovedArtwork(
    crop: ArtworkCrop?,
    fallback: ArtworkFallback? = null,
    modifier: Modifier = Modifier,
    cornerRadius: Float = 16f
) {
    // The final approved Library reference is now the canonical crop source.
    val approvedLayout = ImageBitmap.imageResource(R.drawable.reference_library_final)
    val shape = remember(cornerRadius) { RoundedCornerShape(cornerRadius.dp) }

    Canvas(
        modifier
            .clip(shape)
            .background(Color(0xFF252A2E))
    ) {
        if (crop != null) {
            drawImage(
                image = approvedLayout,
                srcOffset = IntOffset(crop.left, crop.top),
                srcSize = IntSize(crop.width, crop.height),
                dstOffset = IntOffset.Zero,
                dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
            )
            return@Canvas
        }

        when (fallback) {
            ArtworkFallback.NIGHT_SKY -> {
                drawRect(Brush.verticalGradient(listOf(Color(0xFF0A1533), Color(0xFF45275F), Color(0xFF0C1117))))
                repeat(18) { i ->
                    val x = ((i * 37) % 97) / 97f * size.width
                    val y = ((i * 53) % 73) / 73f * size.height * .58f
                    drawCircle(Color.White.copy(alpha = .7f), radius = size.minDimension * .009f, center = Offset(x, y))
                }
                val mountain = Path().apply {
                    moveTo(0f, size.height * .72f)
                    lineTo(size.width * .28f, size.height * .46f)
                    lineTo(size.width * .45f, size.height * .68f)
                    lineTo(size.width * .64f, size.height * .52f)
                    lineTo(size.width, size.height * .76f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(mountain, Color(0xFF111821))
            }
            ArtworkFallback.NATURE -> {
                drawRect(Brush.linearGradient(listOf(Color(0xFF0A2A18), Color(0xFF1B6A35), Color(0xFF08180F))))
                val leaf = Path().apply {
                    moveTo(size.width * .18f, size.height * .77f)
                    cubicTo(size.width * .35f, size.height * .20f, size.width * .78f, size.height * .10f, size.width * .86f, size.height * .26f)
                    cubicTo(size.width * .73f, size.height * .58f, size.width * .44f, size.height * .82f, size.width * .18f, size.height * .77f)
                    close()
                }
                drawPath(leaf, Color(0xFF3E9B57))
                drawLine(Color(0xFFB7E2BE), Offset(size.width*.28f,size.height*.70f), Offset(size.width*.76f,size.height*.26f), strokeWidth=size.minDimension*.018f)
                listOf(.38f to .48f, .55f to .34f, .64f to .58f).forEach { (x,y) ->
                    drawCircle(Color(0xBFE7FFF0), size.minDimension*.035f, Offset(size.width*x,size.height*y))
                }
            }
            ArtworkFallback.CONCERT -> {
                drawRect(Brush.verticalGradient(listOf(Color(0xFFFF8B35), Color(0xFF5A1D13), Color(0xFF0B0B0C))))
                repeat(5) { i ->
                    val x = size.width * (.16f + i*.17f)
                    drawLine(Color(0xAAFFD47A), Offset(x,0f), Offset(x-size.width*.08f,size.height*.58f), strokeWidth=size.minDimension*.025f)
                }
                repeat(7) { i ->
                    val x=size.width*(.08f+i*.145f)
                    drawCircle(Color(0xFF111111), size.minDimension*.08f, Offset(x,size.height*.78f))
                    drawRect(Color(0xFF111111), topLeft=Offset(x-size.width*.045f,size.height*.78f), size=androidx.compose.ui.geometry.Size(size.width*.09f,size.height*.22f))
                }
            }
            ArtworkFallback.CITY -> {
                drawRect(Brush.verticalGradient(listOf(Color(0xFF33125A), Color(0xFF8A2B62), Color(0xFF10131E))))
                repeat(8) { i ->
                    val left=size.width*(i/8f)
                    val w=size.width*.105f
                    val h=size.height*(.18f+((i*29)%45)/100f)
                    drawRect(
                        Color(0xFF101522),
                        topLeft=Offset(left,size.height-h),
                        size=androidx.compose.ui.geometry.Size(w,h)
                    )
                    repeat(3) { r ->
                        drawCircle(Color(0xFFFFC15C), size.minDimension*.012f, Offset(left+w*.35f,size.height-h+size.height*(.05f+r*.07f)))
                    }
                }
            }
            null -> Unit
        }
    }
}
