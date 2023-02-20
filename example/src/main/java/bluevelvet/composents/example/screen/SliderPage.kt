package bluevelvet.composents.example.screen

import androidx.annotation.DrawableRes

/**
 *
 *
 * @author Morteza Taghdisi
 * @since 2023-02-20
 **/

data class SliderPage(
    val title: String,
    val description: String = "",
    @DrawableRes
    val image: Int,
)