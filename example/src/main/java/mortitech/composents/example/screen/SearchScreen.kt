package mortitech.composents.example.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mortitech.composents.example.R
import mortitech.composents.ui.bar.ComposentsSearchBar

/**
 * Search screen
 *
 * @author Morteza Taghdisi
 * @since 2023-02-17
 **/

@Composable
fun SearchScreen() {
    SearchScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenContent() {

    Scaffold { innerPadding ->
        val contentModifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .fillMaxSize()
            .padding(innerPadding)

        Box(
            modifier = contentModifier
        ) {
            ComposentsSearchBar(
                modifier = Modifier.fillMaxWidth(),
                tailingContent = {
                    ProfileImage(
                        drawableResource = R.mipmap.user_avatar,
                        description = "Profile image of user 1",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(32.dp)
                    )
                }
            ) { searchedText ->
                Log.d("Tag", searchedText)
            }
        }
    }
}

@Composable
fun ProfileImage(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}
