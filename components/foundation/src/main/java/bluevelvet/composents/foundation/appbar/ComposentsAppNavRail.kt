package bluevelvet.composents.foundation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bluevelvet.composents.foundation.menu.ComposentsMenuItem

/**
 * Nav Rail for drawer
 *
 * @author Morteza Taghdisi
 * @since 2023-02-13
 **/

@Composable
internal fun ComposentsAppNavRail(
    menuItems: List<ComposentsMenuItem>,
    modifier: Modifier = Modifier,
    selectedItemId: String,
    onMenuItemClick: (ComposentsMenuItem) -> Unit,
) {
    NavigationRail(
        header = {
            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
        },
        modifier = modifier
    ) {
        Spacer(Modifier.weight(1f))
        menuItems.forEach { item ->
            NavigationRailItem(
                selected = item.id == selectedItemId,
                onClick = { onMenuItemClick(item) },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.title) },
                alwaysShowLabel = false
            )
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavRail() {
    ComposentsAppNavRail(
        menuItems = listOf(
            ComposentsMenuItem(id = "favorite", title = "Favorite", icon = Icons.Filled.Favorite),
            ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search),
        ),
        selectedItemId = "favorite",
    ) { }
}
