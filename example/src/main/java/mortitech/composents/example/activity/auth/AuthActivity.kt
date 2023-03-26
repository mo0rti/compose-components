package mortitech.composents.example.activity.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mortitech.composents.example.activity.home.HomeActivity
import mortitech.composents.example.core.createJob
import mortitech.composents.example.ui.theme.ComposentsExampleTheme
import mortitech.composents.ui.pin.PinBox
import mortitech.composents.ui.pin.PinPad
import mortitech.composents.ui.pin.PinPadResult
import mortitech.composents.ui.pin.PinState
import kotlinx.coroutines.delay
import kotlin.random.Random

class AuthActivity : ComponentActivity() {
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
                                            Toast.makeText(this@AuthActivity, "Incorrect pin code", Toast.LENGTH_SHORT).show()
                                        else
                                            startActivity(Intent(this@AuthActivity, HomeActivity::class.java))
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
