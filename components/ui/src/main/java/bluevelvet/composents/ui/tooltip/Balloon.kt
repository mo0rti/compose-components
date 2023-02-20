package bluevelvet.composents.ui.tooltip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import bluevelvet.composents.foundation.shapes.TriangleShape

@Stable
enum class BalloonPosition {
    TOP,
    BOTTOM,
    START,
    END
}

fun Modifier.flipHorizontally(): Modifier = this.then(Modifier.scale(-1f, 1f))
fun Modifier.flipVertically(): Modifier = this.then(Modifier.scale(1f, -1f))

@Composable
fun BalloonBox(
    modifier: Modifier = Modifier,
    text: String,
    position: BalloonPosition,
    showBalloon: Boolean,
    backgroundColor: Color = colorScheme.errorContainer,
    contentColor: Color = colorScheme.inverseSurface,
    content: (@Composable () -> Unit)? = null,
) {
    val arrowSize = remember { 12.dp }
    val borderRadius = remember { 4.dp }

    if (showBalloon) {
        when (position) {
            BalloonPosition.START,
            BalloonPosition.END -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (position == BalloonPosition.START)
                        HorizontalArrow(arrowSize = arrowSize, backgroundColor = backgroundColor, modifier = Modifier.flipHorizontally())
                    BalloonContent(
                        modifier = modifier,
                        text = text,
                        borderRadius = borderRadius,
                        backgroundColor = backgroundColor,
                        contentColor = contentColor,
                        content = content,
                    )
                    if (position == BalloonPosition.END)
                        HorizontalArrow(arrowSize = arrowSize, backgroundColor = backgroundColor)
                }
            }
            BalloonPosition.TOP,
            BalloonPosition.BOTTOM -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (position == BalloonPosition.TOP)
                        VerticalArrow(arrowSize = arrowSize, backgroundColor = backgroundColor)
                    BalloonContent(
                        modifier = modifier,
                        text = text,
                        borderRadius = borderRadius,
                        backgroundColor = backgroundColor,
                        contentColor = contentColor,
                        content = content,
                    )
                    if (position == BalloonPosition.BOTTOM)
                        VerticalArrow(arrowSize = arrowSize, backgroundColor = backgroundColor, modifier = Modifier.flipVertically())
                }
            }
        }
    }
}

@Composable
private fun BalloonContent(
    modifier: Modifier,
    text: String,
    borderRadius: Dp,
    backgroundColor: Color,
    contentColor: Color,
    content: (@Composable () -> Unit)?,
) {
    content?.invoke() ?: run {
        Text(
            text = text,
            modifier = modifier
                .clip(RoundedCornerShape(borderRadius))
                .background(backgroundColor)
                .padding(8.dp),
            style = typography.bodySmall,
            color = contentColor
        )
    }
}

@Composable
private fun VerticalArrow(
    modifier: Modifier = Modifier,
    arrowSize: Dp,
    backgroundColor: Color,
) {
    Box(
        modifier = modifier
            .size(arrowSize)
            .clip(TriangleShape())
            .background(backgroundColor)
    )
}

@Composable
private fun HorizontalArrow(
    modifier: Modifier = Modifier,
    arrowSize: Dp,
    backgroundColor: Color,
) {
    Box(
        modifier = modifier
            .size(arrowSize)
            .rotate(90F)
            .clip(TriangleShape())
            .background(backgroundColor)
    )
}

@Preview
@Composable
private fun PreviewBalloonStart() {
    BalloonBox(
        text = "This is a balloon text",
        position = BalloonPosition.START,
        showBalloon = true
    )
}

@Preview
@Composable
private fun PreviewBalloonTop() {
    BalloonBox(
        text = "This is a balloon text",
        position = BalloonPosition.TOP,
        showBalloon = true
    )
}
