package bluevelvet.composent.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
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
import kotlin.random.Random

/**
 * A composable that displays the app bar with overflow menu items.
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/

data class ComposentMenuItem(
    val id: Int = Random.nextInt(1, 999999999),
    val title: String,
    val icon: ImageVector,
    val showOnlyTextOnOverflow: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    menuItems: List<ComposentMenuItem> = emptyList(),
    overflowMenuItems: List<ComposentMenuItem> = emptyList(),
    onMenuItemClick: (ComposentMenuItem) -> Unit = {},
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
                menuItems.forEach { item ->
                    IconButton(onClick = { onMenuItemClick(item) }) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                        )
                    }
                }
            }

            if (overflowMenuItems.isNotEmpty()) {
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
                    overflowMenuItems.forEach { item ->
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
    )
}

@Composable
fun OverflowMenu(menuItem: ComposentMenuItem) {
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
fun PreviewOverflowMenu() {
    MaterialTheme {
        OverflowMenu(
            ComposentMenuItem(title = "Exit", icon = Icons.Filled.ExitToApp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar without overflow menu items")
@Composable
fun PreviewTopAppBar() {
    MaterialTheme {
        ComposentTopAppBar(
            title = "This is the title",
            menuItems = listOf(
                ComposentMenuItem(title = "Favorite", icon = Icons.Outlined.Favorite),
                ComposentMenuItem(title = "Search", icon = Icons.Filled.Search),
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("App toolbar with overflow menu items")
@Composable
fun PreviewTopAppBarWithOverflowItems() {
    MaterialTheme {
        ComposentTopAppBar(
            title = "This is the title",
            menuItems = listOf(
                ComposentMenuItem(title = "Favorite", icon = Icons.Outlined.Favorite),
                ComposentMenuItem(title = "Search", icon = Icons.Filled.Search),
            ),
            overflowMenuItems = listOf(
                ComposentMenuItem(title = "Exit", icon = Icons.Filled.ExitToApp),
            ),
        )
    }
}