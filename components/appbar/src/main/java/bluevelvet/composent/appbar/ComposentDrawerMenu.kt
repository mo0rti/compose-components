package bluevelvet.composent.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * A composable that displays the app bar with overflow menu items.
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/

data class ComposentDrawerItem(
    val id: Int = Random.nextInt(1, 999999999),
    val title: String,
    val icon: ImageVector,
    val route: String,
    val showOnlyTextOnOverflow: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentDrawerMenu(
    title: String,
    modifier: Modifier = Modifier,
    menuItems: List<ComposentDrawerItem> = emptyList(),
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    onMenuItemClick: (ComposentDrawerItem) -> Unit = {},
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    defaultRoute: String = "",
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var currentRoute by remember { mutableStateOf(defaultRoute) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        style = typography.labelLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                        Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
                    }
                },
                modifier = modifier,
            )
        }
    ) { paddingValues ->
        ModalNavigationDrawer(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            drawerContent = {
                DrawerContent(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState,
                    currentRoute = currentRoute,
                    menuItems = menuItems,
                    onDrawerItemClick = {
                        onMenuItemClick(it)
                        currentRoute = it.route
                    },
                )
            },
            drawerState = drawerState
        ) {
            Row {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    currentRoute: String,
    menuItems: List<ComposentDrawerItem>,
    onDrawerItemClick: (ComposentDrawerItem) -> Unit
) {
    ModalDrawerSheet {
        menuItems.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.title) },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                selected = item.route == currentRoute,
                onClick = {
                    onDrawerItemClick(item)
                    coroutineScope.launch { drawerState.close() }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar without overflow menu items")
@Composable
fun PreviewDrawerTopAppBar() {
    MaterialTheme {
        ComposentDrawerMenu(
            title = "This is the title",
            menuItems = listOf(
                ComposentDrawerItem(title = "Favorite", icon = Icons.Outlined.Favorite, route = "favorite"),
                ComposentDrawerItem(title = "Search", icon = Icons.Filled.Search, route = "search"),
            ),
        ) {
        }
    }
}
