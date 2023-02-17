package bluevelvet.composents.foundation.menu

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Composents Menu item
 *
 * @property id the id of the menu item to be used as a unique identifier to find the selected menu item
 * @property title The title of the menu item
 * @property icon The icon of the menu item
 * @property type type of the menu item can be primary or secondary
 * @property badgeCount the number of badges in the menu item
 **/

data class ComposentsMenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val type: ComposentsMenuItemType = ComposentsMenuItemType.PRIMARY,
    var badgeCount: Int? = null,
)