package bluevelvet.sample.pincode

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import bluevelvet.lib.pincode.view.NumericPad
import bluevelvet.sample.pincode.ui.theme.PinCodeSampleTheme
import bluevelvet.lib.pincode.view.PinBox
import bluevelvet.lib.pincode.view.PinPad
import bluevelvet.lib.pincode.view.PinPadResult

class MainActivity : ComponentActivity() {
    private val tag = "PINCODE-SAMPLE"

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
                        PinBox(pinCode = pincode)
                        PinPad(pinCode = pincode) { result ->
                            pincode = result.pinCode
                            if (result is PinPadResult.EntryFinished) {
                                Toast.makeText(this@MainActivity, "Entry finished", Toast.LENGTH_SHORT).show()
                                Log.d(tag, "Pincode entry is finished, pincode is $pincode")
                            } else {
                                Log.d(tag, "Pincode changed to $pincode")
                            }
                        }
                    }
                }
            }
        }
    }
}
