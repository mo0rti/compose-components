package mortitech.composents.example.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mortitech.composents.example.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

/**
 * Home Screen
 *
 * @author Morteza Taghdisi
 * @since 2023-02-17
 **/

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {

    val pagerState = rememberPagerState()

    val pages = listOf(
        SliderPage(title = "Title 1", image = R.mipmap.place_holder_200w_200h),
        SliderPage(title = "Title 2", image = R.mipmap.place_holder_400w_300h),
        SliderPage(title = "Title 3", image = R.mipmap.place_holder_300w_500h),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            ImagePage(pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            pagerState = pagerState
        )
        Spacer(modifier = Modifier.fillMaxSize())
    }

}

@Composable
private fun ImagePage(page: SliderPage) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        painter = painterResource(id = page.image),
        contentDescription = page.title
    )
}
