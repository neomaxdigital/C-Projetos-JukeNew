package com.juke.mp3player.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

/**
 * Shared JUKE background used by every screen.
 *
 * This deliberately stays independent from screen layout so changing the
 * wallpaper never changes paddings, positions, lists or player controls.
 */
@Composable
fun JukeAppBackground(modifier: Modifier = Modifier) {
    Canvas(modifier) {
        drawRect(Color(0xFF071014))

        fun ribbon(
            path: Path,
            start: Color,
            end: Color,
            startY: Float,
            endY: Float
        ) {
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(start, end),
                    start = Offset(0f, size.height * startY),
                    end = Offset(size.width, size.height * endY)
                )
            )
        }

        // Upper-left charcoal ribbon.
        ribbon(
            Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width * 0.44f, 0f)
                cubicTo(
                    size.width * 0.36f, size.height * 0.040f,
                    size.width * 0.18f, size.height * 0.090f,
                    0f, size.height * 0.145f
                )
                close()
            },
            Color(0xFF1A252C),
            Color(0xFF0E171C),
            0f,
            0.16f
        )

        // Upper-right ribbon; broad and shallow so the header remains calm.
        ribbon(
            Path().apply {
                moveTo(size.width * 0.60f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height * 0.115f)
                cubicTo(
                    size.width * 0.85f, size.height * 0.145f,
                    size.width * 0.70f, size.height * 0.155f,
                    size.width * 0.52f, size.height * 0.195f
                )
                cubicTo(
                    size.width * 0.45f, size.height * 0.210f,
                    size.width * 0.46f, size.height * 0.090f,
                    size.width * 0.60f, 0f
                )
                close()
            },
            Color(0xFF172630),
            Color(0xFF0A1217),
            0.02f,
            0.22f
        )

        // Wide center ribbon.
        ribbon(
            Path().apply {
                moveTo(0f, size.height * 0.33f)
                cubicTo(
                    size.width * 0.16f, size.height * 0.25f,
                    size.width * 0.44f, size.height * 0.24f,
                    size.width * 0.66f, size.height * 0.20f
                )
                cubicTo(
                    size.width * 0.83f, size.height * 0.17f,
                    size.width * 0.94f, size.height * 0.14f,
                    size.width, size.height * 0.10f
                )
                lineTo(size.width, size.height * 0.43f)
                cubicTo(
                    size.width * 0.83f, size.height * 0.50f,
                    size.width * 0.57f, size.height * 0.50f,
                    size.width * 0.31f, size.height * 0.55f
                )
                cubicTo(
                    size.width * 0.16f, size.height * 0.58f,
                    size.width * 0.06f, size.height * 0.62f,
                    0f, size.height * 0.66f
                )
                close()
            },
            Color(0xFF1B2A34),
            Color(0xFF0B1419),
            0.18f,
            0.62f
        )

        // Lower diagonal ribbon.
        ribbon(
            Path().apply {
                moveTo(0f, size.height * 0.69f)
                cubicTo(
                    size.width * 0.16f, size.height * 0.62f,
                    size.width * 0.35f, size.height * 0.59f,
                    size.width * 0.53f, size.height * 0.54f
                )
                cubicTo(
                    size.width * 0.72f, size.height * 0.49f,
                    size.width * 0.89f, size.height * 0.45f,
                    size.width, size.height * 0.40f
                )
                lineTo(size.width, size.height * 0.72f)
                cubicTo(
                    size.width * 0.83f, size.height * 0.77f,
                    size.width * 0.65f, size.height * 0.80f,
                    size.width * 0.47f, size.height * 0.86f
                )
                cubicTo(
                    size.width * 0.27f, size.height * 0.92f,
                    size.width * 0.12f, size.height * 0.95f,
                    0f, size.height
                )
                close()
            },
            Color(0xFF172732),
            Color(0xFF0A1318),
            0.46f,
            0.92f
        )

        // Bottom-most ribbon for continuity behind the navigation area.
        ribbon(
            Path().apply {
                moveTo(0f, size.height * 0.91f)
                cubicTo(
                    size.width * 0.22f, size.height * 0.84f,
                    size.width * 0.45f, size.height * 0.82f,
                    size.width * 0.63f, size.height * 0.78f
                )
                cubicTo(
                    size.width * 0.80f, size.height * 0.75f,
                    size.width * 0.93f, size.height * 0.72f,
                    size.width, size.height * 0.69f
                )
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            },
            Color(0xFF101D24),
            Color(0xFF071014),
            0.74f,
            1f
        )
    }
}
