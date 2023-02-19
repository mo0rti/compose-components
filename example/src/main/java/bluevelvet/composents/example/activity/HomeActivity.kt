package bluevelvet.composents.example.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import bluevelvet.composents.example.createJob
import bluevelvet.composents.foundation.*
import bluevelvet.composents.foundation.button.ProgressButton
import bluevelvet.composents.example.screen.HomeScreen
import bluevelvet.composents.example.screen.InboxScreen
import bluevelvet.composents.example.screen.SearchScreen
import bluevelvet.composents.example.ui.theme.ComposentsExampleTheme
import bluevelvet.composents.foundation.menu.ComposentsMenuItem
import bluevelvet.composents.foundation.menu.ComposentsMenuItemType
import bluevelvet.composents.ui.navigation.ComposentsDrawerMenu
import bluevelvet.composents.ui.navigation.ComposentsTopAppBar
import kotlinx.coroutines.delay

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposentsExampleTheme {
                DrawerMenuLayout()
                //AppbarMenuLayout()
            }
        }
    }

    @Composable
    private fun DrawerMenuLayout() {
        val drawerHomeMenuItem = ComposentsMenuItem(title = "Home", icon = Icons.Filled.Home, id = "home")
        val drawerInboxMenuItem = ComposentsMenuItem(title = "Inbox", icon = Icons.Filled.Inbox, id = "inbox", badgeCount = 4)
        val drawerSearchMenuItem = ComposentsMenuItem(title = "Search", icon = Icons.Filled.Search, id = "search")
        val drawerExitMenuItem = ComposentsMenuItem(title = "Logout", icon = Icons.Filled.ExitToApp, id = "logout")
        val drawerMenuItems = listOf(drawerHomeMenuItem, drawerInboxMenuItem, drawerSearchMenuItem, drawerExitMenuItem)

        var selectedDrawerMenuItem by remember { mutableStateOf(drawerMenuItems.first()) }

        ComposentsDrawerMenu(
            appBarHeadline = "This is the title",
            drawerHeadline = "Choose an item",
            menuItems = drawerMenuItems,
            selectedItemId = selectedDrawerMenuItem.id,
            onMenuItemSelected = {
                selectedDrawerMenuItem = it
            },
        ) {
            when(selectedDrawerMenuItem.id) {
                drawerHomeMenuItem.id -> HomeScreen()
                drawerInboxMenuItem.id -> InboxScreen()
                drawerSearchMenuItem.id -> SearchScreen()
                drawerExitMenuItem.id -> {
                    HomeScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppbarMenuLayout() {
        val favoriteMenu = ComposentsMenuItem(title = "Favorite", icon = Icons.Outlined.Favorite, id = "favorite")
        val searchMenu = ComposentsMenuItem(title = "Search", icon = Icons.Filled.Search, id = "search")
        val settingMenu = ComposentsMenuItem(title = "Settings", icon = Icons.Filled.Settings, id = "settings", type = ComposentsMenuItemType.SECONDARY)
        val exitMenu = ComposentsMenuItem(title = "Logout", icon = Icons.Filled.ExitToApp, id = "logout", type = ComposentsMenuItemType.SECONDARY)

        val appBarMenuItems = listOf(favoriteMenu, searchMenu, settingMenu, exitMenu)

        var isLoading by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                ComposentsTopAppBar(
                    headline = "Head line",
                    tailingMenuItems = appBarMenuItems,
                ) {
                    when (it.id) {
                        favoriteMenu.id -> {}
                        searchMenu.id -> {}
                        exitMenu.id -> {
                            startActivity(Intent(this, AuthActivity::class.java))
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
    }
}
