package mortitech.composents.ui.pin

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import mortitech.composents.ui.theme.PinViewBackgroundColor
import mortitech.composents.ui.theme.PinViewErrorColor
import mortitech.composents.ui.theme.PinViewForegroundColor
import mortitech.composents.ui.theme.PinViewLoadingIndicatorColor

//
// Created by Morteza Taghdisi on 09 Jan 2023.
// Last Modified by Morteza Taghdisi on 09 Jan 2023.
//

data class PinConfiguration(
    val pinLength: Int = 6,
    val backgroundColor: Color = PinViewBackgroundColor,
    val foregroundColor: Color = PinViewForegroundColor,
    val errorColor: Color = PinViewErrorColor,
    val pinBoxConfiguration: PinBoxConfiguration? = null,
)

data class PinBoxConfiguration(
    val isHiddenPin: Boolean = true,
    val boxSize: Dp = 50.dp,
    val boxCornerSize: Dp = 20.dp,
    val hiddenSymbolSize: Dp = 20.dp,
    val loadingIndicatorColor: Color = PinViewLoadingIndicatorColor
)
