package bluevelvet.composents.pinview.view

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bluevelvet.composents.pinview.R
import bluevelvet.composents.pinview.theme.PinBoxFGErrorColor
import bluevelvet.composents.pinview.theme.PinBoxFGNormalColor

/**
 * PinBox View
 *
 * */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PinBox(
    pincode: String = "",
    state: PinState = PinState.Default,
    configuration: PinConfiguration? = null,
) {
    val pinConfiguration = configuration ?: PinConfiguration(
        pinBoxConfiguration = PinBoxConfiguration()
    )

    when (state) {
        PinState.Error,
        PinState.Default
        -> Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            PinBoxDigitEntries(
                pincode = pincode,
                configuration = pinConfiguration,
                state = state
            )
        }
        else -> Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (state) {
                PinState.Loading -> AnimatedVisibility(
                    visible = true,
                    exit = scaleOut()
                ) {
                    PinBoxCircularLoadingIndicator(pinConfiguration.pinBoxConfiguration ?: PinBoxConfiguration())
                }
                PinState.Success -> AnimatedVisibility(
                    visible = true,
                    enter = scaleIn(),
                ) {
                    PinBoxSuccess()
                }
                else -> throw NotImplementedError()
            }
        }
    }
}

@Composable
fun PinBoxDigitEntries(
    pincode: String = "",
    state: PinState,
    configuration: PinConfiguration,
) {
    val pinBoxConfiguration = configuration.pinBoxConfiguration ?: PinBoxConfiguration()

    repeat(configuration.pinLength) {
        Box(
            modifier = Modifier
                .size(pinBoxConfiguration.boxSize)
                .clip(RoundedCornerShape(pinBoxConfiguration.boxRoundedCorner))
                .background(color = pinBoxConfiguration.boxBackgroundColor)
                .padding(pinBoxConfiguration.boxInnerPadding),
            contentAlignment = Alignment.Center
        ) {
            pincode.getOrNull(it)?.let {
                if (pinBoxConfiguration.isPassword) {
                    PinBoxHiddenEntry(
                        configuration = pinBoxConfiguration,
                        state = state
                    )
                } else {
                    Text(
                        textAlign = TextAlign.Center,
                        //style = Typography.,
                        color = if (state is PinState.Error) PinBoxFGErrorColor else PinBoxFGNormalColor,
                        text = it.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun PinBoxHiddenEntry(
    state: PinState,
    configuration: PinBoxConfiguration,
) {
    Box(
        modifier = Modifier
            .size(configuration.boxHiddenSymbolSize)
            .clip(CircleShape)
            .background(if (state is PinState.Error) PinBoxFGErrorColor else configuration.boxHiddenSymbolColor)
    )
}

@Composable
fun PinBoxCircularLoadingIndicator(
    configuration: PinBoxConfiguration
) {
    CircularProgressIndicator(
        modifier = Modifier.size(60.dp),
        color = configuration.loadingIndicatorColor,
        strokeWidth = 8.dp
    )
}

@Composable
fun PinBoxSuccess() {
    Image(
        modifier = Modifier.size(60.dp),
        painter = painterResource(id = R.drawable.ic_rounded_tick),
        contentDescription = "Success check mark"
    )
}

@Preview
@Composable
fun PinBoxPreview() {
    PinBox("134")
}

@Preview
@Composable
fun PinBoxCircularLoadingIndicatorPreview() {
    PinBoxCircularLoadingIndicator(PinBoxConfiguration())
}
