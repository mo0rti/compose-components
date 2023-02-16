package bluevelvet.composents.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import bluevelvet.composent.appbar.*
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme

class HomeActivity : ComponentActivity() {

    private val favoriteMenu = ComposentTopBarMenuItem(title = "Favorite", icon = Icons.Outlined.Favorite, id = "favorite")
    private val searchMenu = ComposentTopBarMenuItem(title = "Search", icon = Icons.Filled.Search, id = "search")
    private val exitMenu = ComposentTopBarMenuItem(title = "Logout", icon = Icons.Filled.ExitToApp, id = "logout", type = ComposentMenuItemType.SUB)

    private val drawerHomeMenuItem = ComposentDrawerItem(title = "Home", icon = Icons.Filled.Home, id = "home")
    private val drawerInboxMenuItem = ComposentDrawerItem(title = "Inbox", icon = Icons.Filled.Inbox, id = "inbox", badgeCount = 4)
    private val drawerExitMenuItem = ComposentDrawerItem(title = "Logout", icon = Icons.Filled.ExitToApp, id = "logout")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawerMenuItems = listOf(drawerHomeMenuItem, drawerInboxMenuItem, drawerExitMenuItem)
        val appBarMenuItems = listOf(favoriteMenu, searchMenu, exitMenu)

        setContent {
            ComposentsExampleTheme {

                var selectedDrawerMenuItem by remember { mutableStateOf(drawerMenuItems.first()) }

                ComposentDrawerMenu(
                    appBarTitle = "This is the title",
                    menuItemsTitle = "Choose an item",
                    menuItems = drawerMenuItems,
                    selectedItemId = selectedDrawerMenuItem.id,
                    onMenuItemSelected = {
                        selectedDrawerMenuItem = it
                    },
                ) {
                    Text("hello ${selectedDrawerMenuItem.title}")
                }
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
