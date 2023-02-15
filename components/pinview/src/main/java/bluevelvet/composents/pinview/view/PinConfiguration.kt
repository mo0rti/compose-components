package bluevelvet.composents.pinview.view

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import bluevelvet.composents.pinview.theme.PinBoxBGColor
import bluevelvet.composents.pinview.theme.PinBoxFGNormalColor
import bluevelvet.composents.pinview.theme.PinBoxProgressIndicatorColor
import bluevelvet.composents.pinview.theme.PinPadBGColor

//
// Created by Morteza Taghdisi on 09 Jan 2023.
// Last Modified by Morteza Taghdisi on 09 Jan 2023.
//

data class PinConfiguration(
    val pinLength: Int = 6,
    val pinBoxConfiguration: PinBoxConfiguration? = null,
    val pinPadConfiguration: PinPadConfiguration? = null,
)

data class PinBoxConfiguration(
    val isPassword: Boolean = true,
    val boxSize: Dp = 50.dp,
    val boxBackgroundColor: Color = PinBoxBGColor,
    val boxRoundedCorner: Dp = 20.dp,
    val boxInnerPadding: Dp = 16.dp,
    val boxHiddenSymbolSize: Dp = 20.dp,
    val boxHiddenSymbolColor: Color = PinBoxFGNormalColor,
    val loadingIndicatorColor: Color = PinBoxProgressIndicatorColor
)

data class PinPadConfiguration(
    val backgroundColor: Color = PinPadBGColor,
)