package bluevelvet.composent.button

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 *
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/

@Composable
fun ProgressButton(
    text: String,
    isInProgress: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isButtonEnabled by remember { mutableStateOf(!isInProgress) }

    Button(
        onClick = {
            isButtonEnabled = false
            onClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        enabled = isButtonEnabled || !isInProgress,
        shape = RoundedCornerShape(50)
    ) {
        Box(
            modifier = Modifier
               .fillMaxWidth()
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center)
            )
            if (isInProgress) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp),
                    color = Yellow
                )
            }
        }
    }
}

@Preview
@Composable
fun ProgressButtonPreview() {
    ProgressButton(text = "Save", false) {
    }
}

@Preview
@Composable
fun ProgressButtonInProgressPreview() {
    ProgressButton(text = "Save", true) {}
}