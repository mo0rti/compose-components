package mortitech.composents.example.activity.home

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mortitech.composents.example.activity.auth.AuthActivity
import mortitech.composents.example.core.createJob
import mortitech.composents.example.ui.theme.ComposentsExampleTheme
import mortitech.composents.foundation.*
import mortitech.composents.foundation.button.ProgressButton
import mortitech.composents.foundation.menu.ComposentsMenuItem
import mortitech.composents.foundation.menu.ComposentsMenuItemType
import mortitech.composents.ui.navigation.ComposentsDrawerMenu
import mortitech.composents.ui.navigation.ComposentsTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposentsExampleTheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()

                DrawerMenuLayout(
                    navController = navController,
                    coroutineScope = coroutineScope
                )
                //AppbarMenuLayout()
            }
        }
    }

    @Composable
    private fun DrawerMenuLayout(
        navController: NavHostController,
        coroutineScope: CoroutineScope,
    ) {
        val drawerHomeMenuItem = ComposentsMenuItem(title = "Home", icon = Icons.Filled.Home, id = Destinations.HOME)
        val drawerInboxMenuItem = ComposentsMenuItem(title = "Inbox", icon = Icons.Filled.Inbox, id = Destinations.INBOX, badgeCount = 4)
        val drawerSearchMenuItem = ComposentsMenuItem(title = "Search", icon = Icons.Filled.Search, id = Destinations.SEARCH)
        val drawerExitMenuItem = ComposentsMenuItem(title = "Logout", icon = Icons.Filled.ExitToApp, id = "logout")
        val drawerMenuItems = listOf(drawerHomeMenuItem, drawerInboxMenuItem, drawerSearchMenuItem, drawerExitMenuItem)

        var selectedDrawerMenuItem by remember { mutableStateOf(drawerMenuItems.first()) }

        ComposentsDrawerMenu(
            appBarHeadline = "This is the title",
            drawerHeadline = "Choose an item",
            menuItems = drawerMenuItems,
            selectedItemId = selectedDrawerMenuItem.id,
            coroutineScope = coroutineScope,
            onMenuItemSelected = {
                if (it.id == drawerExitMenuItem.id) {
                    // TODO: Log out the user
                } else {
                    selectedDrawerMenuItem = it
                    navController.navigate(it.id)
                }
            },
        ) {
            HomeNavGraph(
                navController = navController,
                selectedDrawerItem = selectedDrawerMenuItem
            )

            /* You also can use this approach instead of using NavHostGraph
            when(selectedDrawerMenuItem.id) {
                drawerHomeMenuItem.id -> HomeScreen()
                drawerInboxMenuItem.id -> InboxScreen()
                drawerSearchMenuItem.id -> SearchScreen()
                drawerExitMenuItem.id -> {
                    HomeScreen()
                }
            }
            */
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
