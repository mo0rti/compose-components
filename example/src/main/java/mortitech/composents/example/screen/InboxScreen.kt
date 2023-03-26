package mortitech.composents.example.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import mortitech.composents.ui.tooltip.BalloonBox
import mortitech.composents.ui.tooltip.BalloonPosition

/**
 * Inbox Screen
 *
 * @author Morteza Taghdisi
 * @since 2023-02-17
 **/

@Composable
fun InboxScreen() {
    var showBalloon by remember { mutableStateOf(false) }

    Column {
        Text("Inbox Screen")

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
}