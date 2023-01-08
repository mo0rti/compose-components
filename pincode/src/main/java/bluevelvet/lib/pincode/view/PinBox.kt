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
import bluevelvet.lib.pincode.theme.LightGray
import bluevelvet.lib.pincode.theme.LightGray2
import bluevelvet.lib.pincode.theme.Typography

//
// Created by Morteza Taghdisi on 04 Jan 2023.
// Last Modified by Morteza Taghdisi on 04 Jan 2023.
//

@Composable
fun PinBox(
    pincode: String = "",
    maxPinLength: Int = 6,
    isPinHidden: Boolean = true,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        repeat(maxPinLength) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = LightGray)
                    .padding(16.dp),
                    contentAlignment = Alignment.Center
            ) {
                pincode.getOrNull(it)?.let {
                    if (isPinHidden) {
                        HiddenText()
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
fun HiddenText() {
    Box(
        modifier = Modifier.size(20.dp).clip(CircleShape).background(LightGray2)
    )
}

@Preview
@Composable
fun PreviewPinBox() {
    PinBox("134")
}
