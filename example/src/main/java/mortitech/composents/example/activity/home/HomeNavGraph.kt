package mortitech.composents.example.activity.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mortitech.composents.foundation.menu.ComposentsMenuItem
import mortitech.composents.example.screen.HomeScreen
import mortitech.composents.example.screen.InboxScreen
import mortitech.composents.example.screen.SearchScreen

/**
 * Nav graph for the example app.
 **/

object Destinations {
    const val HOME = "home"
    const val INBOX = "inbox"
    const val SEARCH = "search"
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    selectedDrawerItem: ComposentsMenuItem
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME,
    ) {
        composable(Destinations.HOME) {
            HomeScreen()
        }
        composable(Destinations.INBOX) {
            InboxScreen()
        }
        composable(Destinations.SEARCH) {
            SearchScreen()
        }
    }
}