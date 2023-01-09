package bluevelvet.lib.pincode.view

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import bluevelvet.lib.pincode.theme.Background
import bluevelvet.lib.pincode.theme.LightGray
import bluevelvet.lib.pincode.theme.LightGray2

//
// Created by Morteza Taghdisi on 09 Jan 2023.
// Last Modified by Morteza Taghdisi on 09 Jan 2023.
//

data class PinConfiguration(
    val isHidden: Boolean = true,
    val maxLength: Int = 6,
    val pinBoxSize: Dp = 50.dp,
    val pinBoxBackgroundColor: Color = LightGray,
    val pinBoxRoundedCorner: Dp = 20.dp,
    val pinBoxInnerPadding: Dp = 16.dp,
    val pinBoxHiddenSymbolSize: Dp = 20.dp,
    val pinBoxHiddenSymbolColor: Color = LightGray2,
    val pinPadBackgroundColor: Color = Background
)