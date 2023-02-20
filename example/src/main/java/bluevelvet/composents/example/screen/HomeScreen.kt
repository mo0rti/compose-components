package bluevelvet.composents.example.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import bluevelvet.composents.ui.tooltip.BalloonBox
import bluevelvet.composents.ui.tooltip.BalloonPosition

/**
 * Home Screen
 *
 * @author Morteza Taghdisi
 * @since 2023-02-17
 **/

@Composable
fun HomeScreen() {
    Text("Home Screen")

    var showBalloon by remember { mutableStateOf(false) }

    Button(
        onClick = { showBalloon = !showBalloon }
    ) {
        Text("Show Balloon")
    }

    BalloonBox(
        text = "This is a balloon tooltip",
        position = BalloonPosition.START,
        showBalloon = showBalloon
    )
}