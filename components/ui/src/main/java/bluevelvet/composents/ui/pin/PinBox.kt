package bluevelvet.composents.ui.pin

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bluevelvet.composents.ui.R

/**
 * PinBox is a composable that displays a pin.
 *
 * @param pincode The pin to display.
 * @param state The state of the pin.
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
private fun PinBoxDigitEntries(
    pincode: String = "",
    state: PinState,
    configuration: PinConfiguration,
) {
    val pinBoxConfiguration = configuration.pinBoxConfiguration ?: PinBoxConfiguration()

    repeat(configuration.pinLength) {
        Box(
            modifier = Modifier
                .size(pinBoxConfiguration.boxSize)
                .clip(RoundedCornerShape(pinBoxConfiguration.boxCornerSize))
                .background(color = configuration.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            pincode.getOrNull(it)?.let {
                if (pinBoxConfiguration.isHiddenPin) {
                    HiddenSymbol(
                        configuration = configuration,
                        state = state
                    )
                } else {
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (state is PinState.Error) configuration.errorColor else configuration.foregroundColor,
                        text = it.toString()
                    )
                }
            }
        }
    }
}

@Composable
private fun HiddenSymbol(
    state: PinState,
    configuration: PinConfiguration,
) {
    val pinBoxConfiguration = configuration.pinBoxConfiguration ?: PinBoxConfiguration()
    Box(
        modifier = Modifier
            .size(pinBoxConfiguration.hiddenSymbolSize)
            .clip(CircleShape)
            .background(if (state is PinState.Error) configuration.errorColor else configuration.foregroundColor)
    )
}

@Composable
private fun PinBoxCircularLoadingIndicator(
    configuration: PinBoxConfiguration
) {
    CircularProgressIndicator(
        modifier = Modifier.size(60.dp),
        color = configuration.loadingIndicatorColor,
        strokeWidth = 8.dp
    )
}

@Composable
private fun PinBoxSuccess() {
    Image(
        modifier = Modifier.size(60.dp),
        painter = painterResource(id = R.drawable.ic_rounded_tick),
        contentDescription = "Success check mark"
    )
}

@Preview("Visible pin code")
@Composable
private fun PinBoxVisiblePinPreview() {
    PinBox(
        pincode = "134",
        configuration = PinConfiguration(
            pinBoxConfiguration = PinBoxConfiguration(isHiddenPin = false)
        )
    )
}

@Preview("Hidden pin code")
@Composable
private fun PinBoxHiddenPinPreview() {
    PinBox(
        pincode = "134",
        configuration = PinConfiguration(
            pinBoxConfiguration = PinBoxConfiguration(isHiddenPin = true)
        )
    )
}

@Preview
@Composable
private fun PinBoxCircularLoadingIndicatorPreview() {
    PinBoxCircularLoadingIndicator(PinBoxConfiguration())
}
