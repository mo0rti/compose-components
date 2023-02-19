package bluevelvet.composents.example.activity.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import bluevelvet.composents.example.activity.auth.AuthActivity
import bluevelvet.composents.example.activity.home.HomeActivity
import bluevelvet.composents.example.core.Session
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Keep the splash screen visible for this Activity
        installSplashScreen().setKeepOnScreenCondition { true }
        makeApiCall()
    }

    private fun makeApiCall() = runBlocking {
        // Simulate an api call
        delay(2000)
        goToNextScreen()
    }

    private fun goToNextScreen() {
        startActivity(Intent(this,
            if (Session.isAuthenticated) HomeActivity::class.java else AuthActivity::class.java
        ))
        finish()
    }
}
