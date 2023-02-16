package bluevelvet.composent.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A composable that simplify usage of Drawer navigation menu implementation.
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/


data class ComposentDrawerItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val type: ComposentMenuItemType = ComposentMenuItemType.SUB,
    var badgeCount: Int? = null,
)

/**
 * A composable that simplify usage of Drawer navigation menu implementation.
 *
 * @param appBarTitle String text to be displayed in the app bar
 * @param menuItemsTitle String text to be displayed in the header of drawer menu
 * @param appBarModifier Modifier to be applied to the app bar
 * @param drawerMenuModifier Modifier to be applied to the drawer menu
 * @param menuItems List of ComposentDrawerItems to be displayed in the navigation drawer
 * @param onMenuItemSelected Function to be called when a menu item is selected
 * @param selectedItemId String id of the selected menu item
 * @param coroutineScope The [CoroutineScope] to be used to launch the drawer menu
 * @param badge Composable to be displayed in the badge
 * @param content Composable to be displayed in the content of screen
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentDrawerMenu(
    appBarTitle: String,
    menuItemsTitle: String,
    appBarModifier: Modifier = Modifier,
    drawerMenuModifier: Modifier = Modifier,
    menuItems: List<ComposentDrawerItem> = emptyList(),
    onMenuItemSelected: (ComposentDrawerItem) -> Unit = {},
    selectedItemId: String = "",
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    badge: @Composable ((ComposentDrawerItem) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    var currentSelectedItemId by remember { mutableStateOf(selectedItemId) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        modifier = drawerMenuModifier.fillMaxSize(),
        drawerContent = {
            DrawerMenuItemsContent(
                menuItemsTitle = menuItemsTitle,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                selectedItemId = currentSelectedItemId,
                menuItems = menuItems,
                badge = badge,
                onDrawerItemClick = {
                    onMenuItemSelected(it)
                    currentSelectedItemId = it.id
                },
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = appBarTitle,
                            style = typography.labelLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
                        }
                    },
                    modifier = appBarModifier,
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerMenuItemsContent(
    menuItemsTitle: String,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    menuItems: List<ComposentDrawerItem>,
    selectedItemId: String,
    badge: @Composable ((ComposentDrawerItem) -> Unit)?,
    onDrawerItemClick: (ComposentDrawerItem) -> Unit,
) {
    ModalDrawerSheet {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DrawerMenuItemsHeader(
                menuItemsTitle = menuItemsTitle,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
            )
        }

        menuItems.forEach { item ->
            when (item.type) {
                ComposentMenuItemType.MAIN ->
                    DrawerMenuItemMain(
                        coroutineScope = coroutineScope,
                        drawerState = drawerState,
                        item = item,
                        selectedItemId = selectedItemId,
                        onDrawerItemClick = onDrawerItemClick
                    )
                ComposentMenuItemType.SUB ->
                    DrawerMenuItemSub(
                        coroutineScope = coroutineScope,
                        drawerState = drawerState,
                        menuItem = item,
                        selectedItemId = selectedItemId,
                        badge = badge,
                        onDrawerItemClick = onDrawerItemClick
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerMenuItemSub(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    menuItem: ComposentDrawerItem,
    selectedItemId: String,
    badge: @Composable ((ComposentDrawerItem) -> Unit)?,
    onDrawerItemClick: (ComposentDrawerItem) -> Unit,
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(horizontal = 16.dp),
        label = { Text(menuItem.title) },
        icon = { Icon(imageVector = menuItem.icon, contentDescription = null) },
        selected = menuItem.id == selectedItemId,
        onClick = {
            onDrawerItemClick(menuItem)
            coroutineScope.launch { drawerState.close() }
        },
        badge = {
            badge?.let {
                it(menuItem)
            } ?: run {
                menuItem.badgeCount?.let {
                    if (it > 0)
                        Badge { Text(text = it.toString()) }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerMenuItemMain(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    item: ComposentDrawerItem,
    selectedItemId: String,
    onDrawerItemClick: (ComposentDrawerItem) -> Unit,
) {
    NavigationDrawerItem(
        modifier = Modifier.padding(horizontal = 16.dp),
        label = { Text(item.title) },
        icon = { Icon(imageVector = item.icon, contentDescription = null) },
        selected = item.id == selectedItemId,
        onClick = {
            onDrawerItemClick(item)
            coroutineScope.launch { drawerState.close() }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerMenuItemsHeader(
    menuItemsTitle: String,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
) {
    Text(
        text = menuItemsTitle,
        style = typography.titleMedium,
        color = colorScheme.primary
    )
    IconButton(onClick = { coroutineScope.launch { drawerState.close() } }) {
        Icon(
            imageVector = Icons.Default.MenuOpen,
            contentDescription = null
        )
    }
}

@Preview("Normal state")
@Composable
private fun PreviewDrawerTopAppBar() {
    MaterialTheme {
        ComposentDrawerMenu(
            appBarTitle = "This is the title",
            menuItemsTitle = "menu items title",
            menuItems = listOf(
                ComposentDrawerItem(id = "inbox", title = "Inbox", icon = Icons.Outlined.Inbox),
                ComposentDrawerItem(id = "search", title = "Search", icon = Icons.Filled.Search),
            ),
        ) {
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview("Opened menu")
@Composable
private fun PreviewDrawerContent() {
    MaterialTheme {
        DrawerMenuItemsContent(
            menuItemsTitle = "Menu items title",
            coroutineScope = rememberCoroutineScope(),
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            selectedItemId = "inbox",
            menuItems = listOf(
                ComposentDrawerItem(id = "inbox", title = "Inbox", icon = Icons.Outlined.Inbox, badgeCount = 5),
                ComposentDrawerItem(id = "search", title = "Search", icon = Icons.Filled.Search),
            ),
            badge = null,
        ) { }
    }
}