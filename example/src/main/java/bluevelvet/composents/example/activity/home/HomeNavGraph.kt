package bluevelvet.composents.example.activity.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bluevelvet.composents.foundation.menu.ComposentsMenuItem
import bluevelvet.composents.example.screen.HomeScreen
import bluevelvet.composents.example.screen.InboxScreen

/**
 * Nav graph for the example app.
 *
 * @author Morteza Taghdisi
 * @since 2023-02-17
 **/

object Destinations {
    const val HOME = "home"
    const val INBOX = "inbox"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
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
    }
}