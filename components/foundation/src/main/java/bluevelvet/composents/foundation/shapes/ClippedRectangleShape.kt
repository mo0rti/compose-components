package bluevelvet.composents.foundation.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 *
 * Thanks to juliensalvi: https://medium.com/@juliensalvi/custom-shape-with-jetpack-compose-1cb48a991d42
 * */
class ClippedRectangleShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawPaths(size = size, cornerRadius = cornerRadius)
        )
    }
}

private fun drawPaths(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        reset()
        // Top left arc
        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top = -cornerRadius,
                right = cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width - cornerRadius, y = 0f)
        // Top right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = -cornerRadius,
                right = size.width + cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width, y = size.height - cornerRadius)
        // Bottom right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = size.height - cornerRadius,
                right = size.width + cornerRadius,
                bottom = size.height + cornerRadius
            ),
            startAngleDegrees = 270.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = cornerRadius, y = size.height)
        // Bottom left arc
        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top = size.height - cornerRadius,
                right = cornerRadius,
                bottom = size.height + cornerRadius
            ),
            startAngleDegrees = 0.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = 0f, y = cornerRadius)
        close()
    }
}

@Composable
fun ClippedRectangleComposable(
    modifier: Modifier = Modifier,
    cornerRadius: Int,
    drawBehind: (DrawScope.() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                shape = ClippedRectangleShape(cornerRadius.dp.toPx())
                clip = true
            }
            .background(color = MaterialTheme.colorScheme.surface)
            .drawBehind {
                drawBehind?.invoke(this)
            }
            .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp)
    ) {
        content?.invoke()
    }
}

@Preview
@Composable
fun PreviewClippedRectangle() {
    val surfaceColor = MaterialTheme.colorScheme.inverseSurface
    val cornerRadius = 16
    ClippedRectangleComposable(
        modifier = Modifier,
        cornerRadius = cornerRadius,
        drawBehind = {
            scale(scale = 0.9f) {
                drawPath(
                    path = drawPaths(size = size, cornerRadius = cornerRadius.dp.toPx()),
                    color = surfaceColor,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                )
            }
        }
    ) {
        Text(
            text = "Wonderful Offer",
            modifier = Modifier.padding(horizontal = 10.dp),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
    }
}
