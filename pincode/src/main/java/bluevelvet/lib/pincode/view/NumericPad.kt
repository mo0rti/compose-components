package bluevelvet.lib.pincode.view

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

//
// Created by Morteza Taghdisi on 09 Jan 2023.
// Last Modified by Morteza Taghdisi on 09 Jan 2023.
//

@Composable
fun NumericPad(
    configuration: PinConfiguration = PinConfiguration(),
    completion: (Int) -> Unit
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
        PinButtonType.Digit.Nine
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = androidx.compose.ui.Modifier.background(configuration.pinPadBackgroundColor),
        contentPadding = PaddingValues(1.dp)
    ) {
        items(buttons) { button ->
            Box(
                modifier = Modifier.aspectRatio(1.5f)
            ) {
                Column(modifier = Modifier.fillMaxHeight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    PinButton(data = PinButtonData(button) {
                        completion(button.number)
                    })
                }
            }
        }
    }
}


@Composable
@Preview
fun NumericPadPreview() {
    NumericPad {}
}