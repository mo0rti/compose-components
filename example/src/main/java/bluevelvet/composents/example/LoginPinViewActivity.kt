package bluevelvet.composents.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme
import bluevelvet.composents.ui.pin.PinBox
import bluevelvet.composents.ui.pin.PinPad
import bluevelvet.composents.ui.pin.PinPadResult
import bluevelvet.composents.ui.pin.PinState
import kotlinx.coroutines.*
import kotlin.random.Random

class LoginPinViewActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposentsExampleTheme {
                var pincode by remember { mutableStateOf("") }
                var state: PinState by remember { mutableStateOf(PinState.Default) }

                Scaffold { innerPadding ->
                    val contentPadding = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)

                    Column(
                        modifier = contentPadding,
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.fillMaxWidth(1f))
                        PinBox(pincode = pincode, state = state)
                        PinPad(pincode = pincode) { result ->
                            pincode = result.pinCode
                            when (result) {
                                is PinPadResult.EntryFinished -> {
                                    createJob {
                                        // You can add some delay here so last entered number will be displayed on the pin box
                                        delay(200)

                                        state = PinState.Loading

                                        // Simulating network call or checking for pincode validity
                                        delay(1000)

                                        // Randomizing the pin code validity result
                                        state = if (Random.nextBoolean())
                                            PinState.Success
                                        else
                                            PinState.Error

                                        if (state is PinState.Error)
                                            Toast.makeText(this@LoginPinViewActivity, "Incorrect pin code", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                is PinPadResult.Changed -> {
                                    state = PinState.Default
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
