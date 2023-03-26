package mortitech.composents.ui.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable which allows the user to search.
 *
 * @param modifier the modifier to apply to this search bar container
 * @param placeholder The hint to be displayed inside the search bar
 * @param tailingIcon ImageVector to be displayed at the end of the search bar
 * @param onTailingIconClick A callback to be invoked when the tailing icon is clicked
 * @param tailingContent A composable that is displayed instead of tailing icon
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposentsSearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "What are you looking for?",
    tailingIcon: ImageVector = Icons.Outlined.SupervisedUserCircle,
    onTailingIconClick: () -> Unit = {},
    tailingContent: @Composable (RowScope.() -> Unit)? = null,
    onSearchChange: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.surface, CircleShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier.padding(start = 16.dp),
            tint = MaterialTheme.colorScheme.outline
        )
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchChange(it)
            },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        )
        tailingContent?.invoke(this) ?: run {
            IconButton(onClick = onTailingIconClick) {
                Icon(
                    imageVector = tailingIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Preview
@Composable
fun ComposentSearchBarPreview() {
    ComposentsSearchBar()
}
