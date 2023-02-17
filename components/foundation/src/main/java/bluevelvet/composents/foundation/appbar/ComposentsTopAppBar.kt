package bluevelvet.composents.foundation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import bluevelvet.composents.foundation.menu.ComposentsMenuItem
import bluevelvet.composents.foundation.menu.ComposentsMenuItemType

/**
 * A composable top app bar that displays the app bar with overflow menu items.
 *
 * @param headline The headline text of the app bar.
 * @param headlineContent A composable that defines the content of the app bar headline.
 * @param modifier The modifier of the app bar.
 * @param alwaysShowTextOnOverflow if it's true, only the text on the overflow menu will be displayed.
 * @param tailingMenuItems The tailing menu items of the app bar.
 * @param onTailingMenuItemSelected The callback to be invoked when a tailing menu item is selected.
 * @param leadingNavIconContent The leading navigation icon of the app bar.
 * @param onNavIconClick The callback to be invoked when the leading navigation icon is clicked.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentsTopAppBar(
    modifier: Modifier = Modifier,
    headline: String = "",
    headlineContent: (@Composable () -> Unit)? = null,
    alwaysShowTextOnOverflow: Boolean = false,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    leadingNavIconContent: (@Composable () -> Unit)? = null,
    onNavIconClick: (() -> Unit)? = null,
    tailingMenuItems: List<ComposentsMenuItem> = emptyList(),
    onTailingMenuItemSelected: (ComposentsMenuItem) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var topAppBarHeight by remember { mutableStateOf(0) }

    CenterAlignedTopAppBar(
        title = {
            headlineContent?.invoke() ?:
            run {
                Text(
                    text = headline,
                    style = typography.labelLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
       },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            leadingNavIconContent?.let { it() } ?: run {
                IconButton(onClick = { onNavIconClick?.invoke() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        modifier = modifier
            .padding(0.dp)
            .onSizeChanged { size ->
                topAppBarHeight = size.height
            },
        actions = {
            tailingMenuItems.filter {
                    it.type == ComposentsMenuItemType.PRIMARY
                }.forEach { item ->
                    IconButton(
                        onClick = { onTailingMenuItemSelected(item) },
                        modifier = Modifier.padding(0.dp)
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                }

            tailingMenuItems
                .filter { it.type == ComposentsMenuItemType.SECONDARY }
                .apply {
                    if (isNotEmpty()) {
                        Box {
                            IconButton(onClick = { expanded = true }) {
                                Icon(
                                    imageVector = Icons.Filled.MoreVert,
                                    contentDescription = "More",
                                )
                            }
                            DropdownMenu(
                                offset = DpOffset(8.dp, (-topAppBarHeight).dp),
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                    forEach { item ->
                                        DropdownMenuItem(
                                            onClick = {
                                                onTailingMenuItemSelected(item)
                                                expanded = false
                                            },
                                            text = { OverflowMenu(item, alwaysShowTextOnOverflow) },
                                        )
                                    }
                            }
                        }
                    }
                }
        }
    )
}

@Composable
private fun OverflowMenu(
    menuItem: ComposentsMenuItem,
    onlyTextOnOverflow: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!onlyTextOnOverflow) {
            Icon(imageVector = menuItem.icon, contentDescription = menuItem.title)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = menuItem.title)
    }
}

@Preview("Over flow menu")
@Composable
private fun PreviewOverflowMenu() {
    MaterialTheme {
        OverflowMenu(
            ComposentsMenuItem(id = "exit", title = "Exit", icon = Icons.Filled.ExitToApp),
            false
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar without overflow menu items")
@Composable
private fun PreviewTopAppBar() {
    MaterialTheme {
        ComposentsTopAppBar(
            headline = "This is the title",
            tailingMenuItems = listOf(
                ComposentsMenuItem(id = "favorite", title = "Favorite", icon = Icons.Outlined.Favorite),
                ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar with overflow menu items")
@Composable
private fun PreviewTopAppBarWithOverflowItems() {
    MaterialTheme {
        ComposentsTopAppBar(
            headline = "This is the title",
            tailingMenuItems = listOf(
                ComposentsMenuItem(id = "favorite", title = "Favorite", icon = Icons.Outlined.Favorite),
                ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
                ComposentsMenuItem(id = "exit", title = "Exit", icon = Icons.Filled.ExitToApp, type = ComposentsMenuItemType.SECONDARY),
            )
        )
    }
}