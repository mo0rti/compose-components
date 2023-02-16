package bluevelvet.composent.appbar

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

/**
 * Nav Rail for drawer
 *
 * @author Morteza Taghdisi
 * @since 2023-02-13
 **/

@Composable
internal fun AppNavRail(
    menuItems: List<ComposentDrawerItem>,
    modifier: Modifier = Modifier,
    selectedItemId: String,
    onMenuItemClick: (ComposentDrawerItem) -> Unit,
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
    AppNavRail(
        menuItems = listOf(
            ComposentDrawerItem(id = "favorite", title = "Favorite", icon = Icons.Filled.Favorite),
            ComposentDrawerItem(id = "search", title = "Search", icon = Icons.Filled.Search),
        ),
        selectedItemId = "favorite",
    ) { }
}
