package com.juke.mp3player.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

/**
 * Shared JUKE background.
 *
 * The geometry is normalized to the approved 1080x2400 reference so every
 * screen gets the same wallpaper while remaining independent from UI layout.
 */
@Composable
fun JukeAppBackground(modifier: Modifier = Modifier) {
    Canvas(modifier) {
        drawRect(Color(0xFF050C10))

        fun ribbon(path: Path, start: Color, end: Color, start: Offset, endPoint: Offset) {
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(start, end),
                    start = start,
                    end = endPoint
                )
            )
        }

        // Upper-left sweep.
        ribbon(
            Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width * .47f, 0f)
                cubicTo(
                    size.width * .39f, size.height * .045f,
                    size.width * .22f, size.height * .105f,
                    0f, size.height * .145f
                )
                close()
            },
            Color(0xFF18242B),
            Color(0xFF0A1116),
            Offset(0f, 0f),
            Offset(size.width * .38f, size.height * .15f)
        )

        // Upper-right sweep.
        ribbon(
            Path().apply {
                moveTo(size.width * .46f, size.height * .22f)
                cubicTo(
                    size.width * .62f, size.height * .18f,
                    size.width * .78f, size.height * .095f,
                    size.width, size.height * .055f
                )
                lineTo(size.width, size.height * .165f)
                cubicTo(
                    size.width * .82f, size.height * .205f,
                    size.width * .66f, size.height * .25f,
                    size.width * .52f, size.height * .29f
                )
                cubicTo(
                    size.width * .46f, size.height * .30f,
                    size.width * .43f, size.height * .275f,
                    size.width * .46f, size.height * .22f
                )
                close()
            },
            Color(0xFF13222B),
            Color(0xFF071015),
            Offset(size.width, size.height * .06f),
            Offset(size.width * .48f, size.height * .28f)
        )

        // Broad center sweep, matching the approved library reference.
        ribbon(
            Path().apply {
                moveTo(0f, size.height * .255f)
                cubicTo(
                    size.width * .20f, size.height * .22f,
                    size.width * .40f, size.height * .22f,
                    size.width * .58f, size.height * .27f
                )
                cubicTo(
                    size.width * .76f, size.height * .32f,
                    size.width * .90f, size.height * .39f,
                    size.width, size.height * .445f
                )
                lineTo(size.width, size.height * .565f)
                cubicTo(
                    size.width * .84f, size.height * .52f,
                    size.width * .67f, size.height * .47f,
                    size.width * .49f, size.height * .43f
                )
                cubicTo(
                    size.width * .29f, size.height * .385f,
                    size.width * .12f, size.height * .37f,
                    0f, size.height * .405f
                )
                close()
            },
            Color(0xFF172732),
            Color(0xFF071015),
            Offset(0f, size.height * .29f),
            Offset(size.width, size.height * .53f)
        )

        // Lower-middle sweep.
        ribbon(
            Path().apply {
                moveTo(0f, size.height * .635f)
                cubicTo(
                    size.width * .15f, size.height * .57f,
                    size.width * .34f, size.height * .535f,
                    size.width * .53f, size.height * .50f
                )
                cubicTo(
                    size.width * .72f, size.height * .465f,
                    size.width * .89f, size.height * .445f,
                    size.width, size.height * .405f
                )
                lineTo(size.width, size.height * .565f)
                cubicTo(
                    size.width * .82f, size.height * .625f,
                    size.width * .64f, size.height * .66f,
                    size.width * .47f, size.height * .71f
                )
                cubicTo(
                    size.width * .28f, size.height * .765f,
                    size.width * .12f, size.height * .84f,
                    0f, size.height * .905f
                )
                close()
            },
            Color(0xFF15252F),
            Color(0xFF071015),
            Offset(0f, size.height * .67f),
            Offset(size.width, size.height * .47f)
        )

        // Bottom sweep.
        ribbon(
            Path().apply {
                moveTo(0f, size.height)
                cubicTo(
                    size.width * .16f, size.height * .94f,
                    size.width * .31f, size.height * .86f,
                    size.width * .45f, size.height * .80f
                )
                cubicTo(
                    size.width * .63f, size.height * .72f,
                    size.width * .81f, size.height * .69f,
                    size.width, size.height * .64f
                )
                lineTo(size.width, size.height * .80f)
                cubicTo(
                    size.width * .82f, size.height * .84f,
                    size.width * .66f, size.height * .88f,
                    size.width * .52f, size.height * .94f
                )
                lineTo(size.width * .40f, size.height)
                close()
            },
            Color(0xFF172833),
            Color(0xFF071015),
            Offset(0f, size.height),
            Offset(size.width, size.height * .67f)
        )
    }
}
