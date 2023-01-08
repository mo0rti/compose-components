package bluevelvet.app.pincode

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bluevelvet.app.pincode.ui.theme.PinCodeSampleTheme
import bluevelvet.lib.pincode.view.PinBox
import bluevelvet.lib.pincode.view.PinPad
import bluevelvet.lib.pincode.view.PinPadResult

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinCodeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var pincode by remember { mutableStateOf("") }

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.fillMaxWidth(1f))
                        PinBox(pincode)
                        PinPad(pincode) {
                            pincode = it.pinCode
                            if (it is PinPadResult.ChangeFinished) {
                                Log.i("Pin Pad", "----> ChangeFinished")
                            }
                        }
                    }
                }
            }
        }
    }
}
