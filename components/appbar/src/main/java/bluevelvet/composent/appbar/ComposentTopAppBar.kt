package bluevelvet.composent.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ComposentTopBarMenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val type: ComposentMenuItemType = ComposentMenuItemType.MAIN,
    val showOnlyTextOnOverflow: Boolean = false,
)

/**
 * A composable top app bar that displays the app bar with overflow menu items.
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    menuItems: List<ComposentTopBarMenuItem> = emptyList(),
    onMenuItemClick: (ComposentTopBarMenuItem) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        actions = {
            Row {
                menuItems
                    .filter {
                        it.type == ComposentMenuItemType.MAIN
                    }.forEach { item ->
                        IconButton(onClick = { onMenuItemClick(item) }) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                            )
                        }
                    }
            }

            menuItems
                .filter { it.type == ComposentMenuItemType.SUB }
                .apply {
                    if (isNotEmpty()) {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More",
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            forEach { item ->
                                DropdownMenuItem(
                                    onClick = {
                                        onMenuItemClick(item)
                                        expanded = false
                                    },
                                    text = { OverflowMenu(item) },
                                )
                            }
                        }
                    }
                }
        }
    )
}

@Composable
private fun OverflowMenu(menuItem: ComposentTopBarMenuItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!menuItem.showOnlyTextOnOverflow) {
            Icon(imageVector = menuItem.icon, contentDescription = menuItem.title)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = menuItem.title)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Over flow menu")
@Composable
private fun PreviewOverflowMenu() {
    MaterialTheme {
        OverflowMenu(
            ComposentTopBarMenuItem(id = "exit", title = "Exit", icon = Icons.Filled.ExitToApp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar without overflow menu items")
@Composable
private fun PreviewTopAppBar() {
    MaterialTheme {
        ComposentTopAppBar(
            title = "This is the title",
            menuItems = listOf(
                ComposentTopBarMenuItem(id = "favorite", title = "Favorite", icon = Icons.Outlined.Favorite),
                ComposentTopBarMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar with overflow menu items")
@Composable
private fun PreviewTopAppBarWithOverflowItems() {
    MaterialTheme {
        ComposentTopAppBar(
            title = "This is the title",
            menuItems = listOf(
                ComposentTopBarMenuItem(id = "favorite", title = "Favorite", icon = Icons.Outlined.Favorite),
                ComposentTopBarMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
                ComposentTopBarMenuItem(id = "exit", title = "Exit", icon = Icons.Filled.ExitToApp, type = ComposentMenuItemType.SUB),
            )
        )
    }
}