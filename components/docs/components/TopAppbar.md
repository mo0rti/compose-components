<h1 align="center">Toolbar with overflow menu items</h1>

<div align="center">
  <img src="../images/topappbar.png" alt="Toolbar with overflow menu items" width=350>
</div>
<br>

This repo is a jetpack compose library to easily create a toolbar with overflow menu items

## Getting Started
[![](https://jitpack.io/v/mo0rti/compose-components.svg)](https://jitpack.io/#mo0rti/compose-components)
[![Project Status: Active – The project has reached a stable, usable state and is being actively developed.](https://www.repostatus.org/badges/latest/active.svg)](https://www.repostatus.org/#active)
[![Compatible with Compose — 1.4.0](https://img.shields.io/badge/Compatible%20with%20Compose-1.4.0-brightgreen)](https://developer.android.com/jetpack/androidx/releases/compose-foundation#1.4.0)

## Usage

Examples are in the [source code](../../example/src/main/java/bluevelvet/composents/example/HomeActivity.kt).

```kotlin
private val favoriteMenu = ComposentsMenuItem(id = "title", title = "Favorite", icon = Icons.Outlined.Favorite)
private val searchMenu = ComposentsMenuItem(id = "search", title = "Search", icon = Icons.Filled.Search)
private val exitMenu = ComposentsMenuItem(id = "logout", title = "Logout", icon = Icons.Filled.ExitToApp, type = ComposentsMenuItemType.SECONDARY) // overflow menu item

Scaffold(
    topBar = {
        ComposentsTopAppBar(
            "My Title",
            menuItems = menuItems,
        ) {
            when (it.id) {
                favoriteMenu.id -> {}
                searchMenu.id -> {}
                exitMenu.id -> {
                    startActivity(Intent(this, LoginPinViewActivity::class.java))
                }
            }
        }
    }
)
```


## Parameters

| Property                            | Type                      | Default      | Description                                                |
|-------------------------------------|---------------------------|--------------|------------------------------------------------------------|
| headline | `String`                  | empty String | Title to be shown on App bar                               |
| headlineContent | `Compose`                  |null | Your custom headline component to be shown on App bar                               |
| tailingMenuItems                           | `List<ComposentsMenuItem>` | emptyList()  | Menu items should be visible on app bar                    |
| onTailingMenuItemSelected | `Callback`                |              | Callback to detect which menu item has been tapped by user |

<br/>
