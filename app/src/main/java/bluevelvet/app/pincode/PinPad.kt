package bluevelvet.app.pincode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bluevelvet.app.pincode.ui.theme.Background
import bluevelvet.app.pincode.ui.theme.PinCodeTheme

/**
 * Created by Morteza Taghdisi on 29/12/2022
 * https://github.com/mo0rti
 */

@Composable
fun PinPad() {

    val buttons = listOf(
        PinButtonType.Digit.One,
        PinButtonType.Digit.Two,
        PinButtonType.Digit.Three,
        PinButtonType.Digit.Four,
        PinButtonType.Digit.Five,
        PinButtonType.Digit.Six,
        PinButtonType.Digit.Seven,
        PinButtonType.Digit.Eight,
        PinButtonType.Digit.Nine,
        PinButtonType.Biometric,
        PinButtonType.Digit.Zero,
        PinButtonType.Delete,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.background(Background),
        contentPadding = PaddingValues(1.dp)
    ) {
        items(buttons) { button ->
            Box(
                modifier = Modifier.aspectRatio(1.5f)
            ) {
                Column(modifier = Modifier.fillMaxHeight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    PinButton(data = PinButtonData(button))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinPadPreview() {
    PinCodeTheme {
        PinPad()
    }
}