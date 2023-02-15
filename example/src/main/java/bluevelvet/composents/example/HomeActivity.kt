package bluevelvet.composents.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import bluevelvet.composent.appbar.ComposentMenuItem
import bluevelvet.composent.appbar.ComposentTopAppBar
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
class HomeActivity : ComponentActivity() {

    private val favoriteMenu = ComposentMenuItem(title = "Favorite", icon = Icons.Outlined.Favorite)
    private val searchMenu = ComposentMenuItem(title = "Search", icon = Icons.Filled.Search)
    private val exitMenu = ComposentMenuItem(title = "Logout", icon = Icons.Filled.ExitToApp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuItems = listOf(favoriteMenu, searchMenu)
        val overflowMenuItems = listOf(exitMenu)

        setContent {
            ComposentsExampleTheme {
                Scaffold(
                    topBar = {
                        ComposentTopAppBar(
                            "My Title",
                            menuItems = menuItems,
                            overflowMenuItems = overflowMenuItems,
                        ) {
                            when (it.id) {
                                favoriteMenu.id -> {}
                                searchMenu.id -> {}
                                exitMenu.id -> {
                                    startActivity(Intent(this, LoginPinViewActivity::class.java))
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    val contentModifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)

                    Box(
                        modifier = contentModifier
                    )
                }
            }
        }
    }
}