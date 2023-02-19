package bluevelvet.composents.example.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposentsExampleTheme {

            }
        }
    }
}
