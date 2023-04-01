package mortitech.composents.foundation.expandable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCard(
    title: String,
    content: String
) {
    var expendedState by remember {
        mutableStateOf(value = false)
    }
    val rotationState by animateFloatAsState(targetValue = (if (expendedState) 180f else 0f))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(4.dp),
        onClick = {
            expendedState = !expendedState
        })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expendedState = !expendedState
                    }) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Drop down icon is here"
                    )
                }
            }
            if (!expendedState) {
                Text(
                    text = content,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

@Preview
@Composable
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "My Accordion",
        content = "There once was a ship that put to sea The name of the ship was the Billy O' Tea The winds blew up, her bow dipped down Oh blow, my bully boys, blow"
    )
}