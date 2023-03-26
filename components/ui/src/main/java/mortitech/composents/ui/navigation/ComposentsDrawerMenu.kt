package mortitech.composents.ui.navigation

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mortitech.composents.foundation.menu.ComposentsMenuItem
import mortitech.composents.foundation.menu.ComposentsMenuItemType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A composable that simplify usage of Drawer navigation menu implementation.
 *
 * @param appBarHeadline String text to be displayed in the app bar
 * @param appBarHeadlineContent Composable content to be displayed in the app bar
 * @param drawerHeadline String text to be displayed in the header of drawer menu
 * @param drawerHeadlineContent Composable content to be displayed in the header of drawer menu
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
fun ComposentsDrawerMenu(
    appBarModifier: Modifier = Modifier,
    appBarHeadline: String = "",
    appBarHeadlineContent: @Composable (() -> Unit)? = null,
    drawerMenuModifier: Modifier = Modifier,
    drawerHeadline: String = "",
    drawerHeadlineContent: @Composable (() -> Unit)? = null,
    menuItems: List<ComposentsMenuItem> = emptyList(),
    onMenuItemSelected: (ComposentsMenuItem) -> Unit = {},
    selectedItemId: String = "",
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    badge: @Composable ((ComposentsMenuItem) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        modifier = drawerMenuModifier.fillMaxSize(),
        drawerContent = {
            DrawerMenuItemsContent(
                menuItemsTitle = drawerHeadline,
                drawerTitleContent = drawerHeadlineContent,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                selectedItemId = selectedItemId,
                menuItems = menuItems,
                badge = badge,
                onDrawerItemClick = {
                    onMenuItemSelected(it)
                },
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { AppBarHeadLineContent(appBarHeadline = appBarHeadline, appBarHeadlineContent = appBarHeadlineContent) },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
                        }
                    },
                    modifier = appBarModifier,
                )
            }
        ) { paddingValues ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                content()
            }
        }
    }
}

@Composable
private fun AppBarHeadLineContent(
    appBarHeadline: String = "",
    appBarHeadlineContent: @Composable (() -> Unit)? = null,
) {
    appBarHeadlineContent?.let {
        it()
    } ?: run {
        Text(
            text = appBarHeadline,
            style = typography.labelLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerMenuItemsContent(
    menuItemsTitle: String,
    drawerTitleContent: @Composable (() -> Unit)?,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    menuItems: List<ComposentsMenuItem>,
    selectedItemId: String,
    badge: @Composable ((ComposentsMenuItem) -> Unit)?,
    onDrawerItemClick: (ComposentsMenuItem) -> Unit,
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
                drawerTitleContent = drawerTitleContent,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
            )
        }

        menuItems.forEach { item ->
            when (item.type) {
                ComposentsMenuItemType.SECONDARY ->
                    DrawerMenuItemHeader(
                        coroutineScope = coroutineScope,
                        drawerState = drawerState,
                        item = item,
                        selectedItemId = selectedItemId,
                        onDrawerItemClick = onDrawerItemClick
                    )
                ComposentsMenuItemType.PRIMARY ->
                    DrawerMenuItemMain(
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
private fun DrawerMenuItemMain(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    menuItem: ComposentsMenuItem,
    selectedItemId: String,
    badge: @Composable ((ComposentsMenuItem) -> Unit)?,
    onDrawerItemClick: (ComposentsMenuItem) -> Unit,
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
private fun DrawerMenuItemHeader(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    item: ComposentsMenuItem,
    selectedItemId: String,
    onDrawerItemClick: (ComposentsMenuItem) -> Unit,
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
    drawerTitleContent: @Composable (() -> Unit)?,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
) {
    drawerTitleContent?.let {
        it()
    } ?: run {
        Text(
            text = menuItemsTitle,
            style = typography.titleMedium,
            color = colorScheme.primary
        )
    }

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
        ComposentsDrawerMenu(
            appBarHeadline = "This is the title",
            drawerHeadline = "menu items title",
            menuItems = listOf(
                ComposentsMenuItem(id = "inbox", title = "Inbox", icon = Icons.Outlined.Inbox),
                ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
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
            drawerTitleContent = null,
            coroutineScope = rememberCoroutineScope(),
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            selectedItemId = "inbox",
            menuItems = listOf(
                ComposentsMenuItem(id = "inbox", title = "Inbox", icon = Icons.Outlined.Inbox, badgeCount = 5),
                ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
            ),
            badge = null,
        ) { }
    }
}