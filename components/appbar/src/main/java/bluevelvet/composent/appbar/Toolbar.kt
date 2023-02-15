package bluevelvet.composent.appbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 *
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    navigationIconClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewTopAppBar() {
    MaterialTheme {
        TopAppBar("This is the title") {}
    }
}