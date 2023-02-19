package bluevelvet.composents.ui.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bluevelvet.composents.foundation.menu.ComposentsMenuItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Nav Rail to be displayed on the left side of the screen.
 *
 * @param menuItems List of ComposentDrawerItems to be displayed in the navigation drawer
 * @param onMenuItemSelected Function to be called when a menu item is selected
 * @param selectedItemId String id of the selected menu item
 * @param coroutineScope The [CoroutineScope] to be used to launch the drawer menu
 * @param headerContent A composable to be displayed in the header of the navigation rail
 * @param footerContent A composable to be displayed in the bottom of the navigation rail
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentsAppNavRail(
    modifier: Modifier = Modifier,
    menuItems: List<ComposentsMenuItem>,
    selectedItemId: String,
    onMenuItemSelected: (ComposentsMenuItem) -> Unit,
    headerContent: @Composable (ColumnScope.() -> Unit)? = null,
    footerContent: @Composable (() -> Unit)? = null,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open),
) {
    NavigationRail(
        header = {
            HeaderContent(
                scope = this,
                headerContent = headerContent,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
            )
        },
        modifier = modifier.fillMaxHeight()
    ) {
        menuItems.forEach { item ->
            NavigationRailItem(
                selected = item.id == selectedItemId,
                onClick = {
                    onMenuItemSelected(item)
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.title) },
                alwaysShowLabel = false
            )
        }
        Spacer(Modifier.weight(1f))
        footerContent?.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeaderContent(
    scope: ColumnScope,
    headerContent: @Composable (ColumnScope.() -> Unit)?,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
) {
    headerContent?.invoke(scope) ?: run {
        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewAppNavRail() {
    ComposentsAppNavRail(
        menuItems = listOf(
            ComposentsMenuItem(id = "favorite", title = "Favorite", icon = Icons.Filled.Favorite),
            ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
        ),
        selectedItemId = "favorite",
        onMenuItemSelected = {}
    )
}
