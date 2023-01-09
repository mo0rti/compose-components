package bluevelvet.lib.pincode.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bluevelvet.lib.pincode.theme.Typography

//
// Created by Morteza Taghdisi on 04 Jan 2023.
// Last Modified by Morteza Taghdisi on 04 Jan 2023.
//

@Composable
fun PinBox(
    pinCode: String = "",
    configuration: PinConfiguration = PinConfiguration(),
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        repeat(configuration.maxLength) {
            Box(
                modifier = Modifier
                    .size(configuration.pinBoxSize)
                    .clip(RoundedCornerShape(configuration.pinBoxRoundedCorner))
                    .background(color = configuration.pinBoxBackgroundColor)
                    .padding(configuration.pinBoxInnerPadding),
                    contentAlignment = Alignment.Center
            ) {
                pinCode.getOrNull(it)?.let {
                    if (configuration.isHidden) {
                        HiddenSymbol(configuration)
                    } else {
                        Text(
                            textAlign = TextAlign.Center,
                            style = Typography.h3,
                            text = it.toString()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HiddenSymbol(configuration: PinConfiguration) {
    Box(
        modifier = Modifier
            .size(configuration.pinBoxHiddenSymbolSize)
            .clip(CircleShape)
            .background(configuration.pinBoxHiddenSymbolColor)
    )
}

@Preview
@Composable
fun PreviewPinBox() {
    PinBox("134")
}
