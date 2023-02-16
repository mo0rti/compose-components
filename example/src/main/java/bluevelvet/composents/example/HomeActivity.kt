package bluevelvet.composents.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import bluevelvet.composent.appbar.*
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
class HomeActivity : ComponentActivity() {

    private val favoriteMenu = ComposentMenuItem(title = "Favorite", icon = Icons.Outlined.Favorite)
    private val searchMenu = ComposentMenuItem(title = "Search", icon = Icons.Filled.Search)
    private val exitMenu = ComposentMenuItem(title = "Logout", icon = Icons.Filled.ExitToApp)

    private val favoriteMenu2 = ComposentDrawerItem(title = "Favorite", icon = Icons.Outlined.Favorite, route = "favorite")
    private val searchMenu2 = ComposentDrawerItem(title = "Search", icon = Icons.Filled.Search, route = "search")
    private val exitMenu2 = ComposentDrawerItem(title = "Logout", icon = Icons.Filled.ExitToApp, route = "logout")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuItems = listOf(favoriteMenu, searchMenu)
        val overflowMenuItems = listOf(exitMenu)

        setContent {
            ComposentsExampleTheme {

                ComposentDrawerMenu(
                    title = "This is the title",
                    menuItems = listOf(favoriteMenu2, searchMenu2, exitMenu2),
                    defaultRoute = "favorite",
                    onMenuItemClick = {

                    }
                ) {
                    Text("hello")
                }

                /*
                var isLoading by remember { mutableStateOf(false) }

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
                    ) {
                        ProgressButton(
                            text = "Save",
                            isInProgress = isLoading,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        ) {
                            createJob {
                                isLoading = true
                                delay(3000)
                                isLoading = false
                            }
                        }
                    }
                }
                */
            }
        }
    }
}
