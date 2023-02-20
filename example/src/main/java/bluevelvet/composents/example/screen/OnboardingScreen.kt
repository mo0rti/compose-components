package bluevelvet.composents.example.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import bluevelvet.composents.example.R
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
fun OnboardingScreen() {

    val pagerState = rememberPagerState()

    val pages = listOf(
        SliderPage("Title 1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ip", R.mipmap.slider_image_1),
        SliderPage("Title 2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ip", R.mipmap.slider_image_2),
        SliderPage("Title 3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ip", R.mipmap.slider_image_3),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
    }

}

@Composable
private fun PagerScreen(page: SliderPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = page.image),
            contentDescription = page.title
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = page.title,
            style = typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = page.description,
            style = typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
