package mortitech.composents.foundation.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class TriangleShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val path = Path().apply {
            moveTo(0f, size.height)
            lineTo(size.width / 2, 0f)
            lineTo(size.width, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun TriangleShapeComposable(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null
) {
    Box(modifier =
        modifier
            .size(60.dp)
            .clip(TriangleShape())
            .background(colorScheme.surface)
    ) {
        content?.invoke()
    }
}

@Preview
@Composable
fun TriangleShapeComposablePreview() {
    TriangleShapeComposable()
}