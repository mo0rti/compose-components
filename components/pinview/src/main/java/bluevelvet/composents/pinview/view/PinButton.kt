package bluevelvet.composents.pinview.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bluevelvet.composents.pinview.theme.PinButtonAlphabetColor
import bluevelvet.composents.pinview.theme.PinButtonBGColor
import bluevelvet.composents.pinview.R

/**
 * Created by Morteza Taghdisi on 29/12/2022
 * https://github.com/mo0rti
 */

internal sealed class PinButtonType(
    @DrawableRes
    val image: Int,
) {
    sealed class Digit(
        val number: Int,
        digitImage: Int,
        val alphabet: String = "",
    ): PinButtonType(digitImage) {
        object One: Digit(1, R.drawable.ic_number_1)
        object Two: Digit(2, R.drawable.ic_number_2, "ABC")
        object Three: Digit(3, R.drawable.ic_number_3, "DEF")
        object Four: Digit(4, R.drawable.ic_number_4, "GHI")
        object Five: Digit(5, R.drawable.ic_number_5, "JKL")
        object Six: Digit(6, R.drawable.ic_number_6, "MNO")
        object Seven: Digit(7, R.drawable.ic_number_7, "PQRS")
        object Eight: Digit(8, R.drawable.ic_number_8, "TUV")
        object Nine: Digit(9, R.drawable.ic_number_9, "WXYZ")
        object Zero: Digit(0, R.drawable.ic_number_0)
    }

    object Delete: PinButtonType(R.drawable.ic_del)
    object Biometric: PinButtonType(R.drawable.ic_finger_print)
}

internal data class PinButtonData(
    val type: PinButtonType,
    val callback: (() -> Unit)? = null
)

@Composable
internal fun PinButton(data: PinButtonData) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .clickable {
                data.callback?.invoke()
            }
            .padding(all = 1.dp)
            .background(PinButtonBGColor)
            .padding(all = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = data.type.image),
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (data.type is PinButtonType.Digit) {
            Text(
                color = PinButtonAlphabetColor,
                text = data.type.alphabet,
                fontSize = 8.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    PinButton(PinButtonData(PinButtonType.Digit.Three))
}
