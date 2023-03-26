package mortitech.composents.ui.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A sealed class that represents the result of a user interaction.
 */
sealed class PinPadResult(open val pinCode: String) {
    data class Changed(override val pinCode: String): PinPadResult(pinCode)
    data class EntryFinished(override val pinCode: String): PinPadResult(pinCode)
}

/**
 * PinPad component is a composable that allows the user to input a pin code.
 */
@Composable
fun PinPad(
    pincode: String,
    configuration: PinConfiguration = PinConfiguration(),
    completion: (PinPadResult) -> Unit
) {
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
        modifier = Modifier.background(configuration.backgroundColor),
        contentPadding = PaddingValues(1.dp)
    ) {
        items(buttons) { button ->
            Box(
                modifier = Modifier.aspectRatio(1.5f)
            ) {
                Column(modifier = Modifier.fillMaxHeight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    PinButton(data = PinButtonData(button) {
                        when {
                            button is PinButtonType.Digit && (pincode.length < configuration.pinLength) ->  {
                                (pincode + button.number.toString()).apply {
                                    if (length == configuration.pinLength)
                                        completion(PinPadResult.EntryFinished(this))
                                    else
                                        completion(PinPadResult.Changed(this))
                                }
                            }
                            button is PinButtonType.Delete -> {
                                if (pincode.isNotBlank())
                                    completion(PinPadResult.Changed(pincode.dropLast(1)))
                            }
                            button is PinButtonType.Biometric -> {
                                // TODO: needs to be implemented
                            }
                        }
                    }, configuration)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinPadPreview() {
    PinPad("") {}
}