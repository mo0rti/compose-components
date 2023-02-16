package bluevelvet.composent.appbar

/**
 * Composent Menu item type
 *
 * @author Morteza Taghdisi
 * @since 2023-02-16
 **/

enum class ComposentMenuItemType {
    /**
     * For an Appbar to be always visible, for a drawer to be shown as a header and non-clickable item
     * */
    MAIN,

    /**
     * For an Appbar to show as an overflow menu item, for a drawer to show as a clickable item
     * */
    SUB
}
