package com.theChance.myapplication.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.theChance.myapplication.ui.modifiers.calculateCurrentOffsetForPage
import com.theChance.myapplication.ui.theme.space40
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PizzaSlider(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pizzas: List<Painter>,
    pizzaSize: Dp,
    //content: @Composable () -> Unit
) {
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        itemSpacing = (-70).dp
    ) { index ->
        val pageOffset = pagerState.calculateCurrentOffsetForPage(index)

        Box(
            Modifier
                .graphicsLayer {
                    scaleX = 1f - pageOffset.coerceIn(-0.33f, 0.33f).absoluteValue
                    scaleY = 1f - pageOffset.coerceIn(-0.33f, 0.33f).absoluteValue
                    alpha = 1f - pageOffset.coerceIn(-0.33f, 0.33f).absoluteValue
                }
                .aspectRatio(0.8f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = pizzas[index],
                contentDescription = null,
                modifier = Modifier
                    .size(pizzaSize)
                    .padding(horizontal = space40)
            )

            //content()
        }
    }
}
