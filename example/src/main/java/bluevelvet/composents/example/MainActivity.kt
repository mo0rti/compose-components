package bluevelvet.composents.example

import android.os.Bundle
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
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme
import bluevelvet.composents.pinview.view.PinBox
import bluevelvet.composents.pinview.view.PinPad
import bluevelvet.composents.pinview.view.PinPadResult
import bluevelvet.composents.pinview.view.PinState
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposentsExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    var pincode by remember { mutableStateOf("") }
                    var state: PinState by remember { mutableStateOf(PinState.Default) }

                    Column(
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
                                            Toast.makeText(this@MainActivity, "Incorrect pin code", Toast.LENGTH_SHORT).show()
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

private fun createJob(completion: suspend () -> Unit) = CoroutineScope(Job() + Dispatchers.Main).launch { completion() }
