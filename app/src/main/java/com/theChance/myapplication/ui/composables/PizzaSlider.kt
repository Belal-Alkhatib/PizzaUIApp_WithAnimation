package com.theChance.myapplication.ui.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.theChance.myapplication.ui.home.PizzaSize
import com.theChance.myapplication.ui.modifiers.calculateCurrentOffsetForPage
import com.theChance.myapplication.ui.theme.pizzaLarge
import com.theChance.myapplication.ui.theme.pizzaMedium
import com.theChance.myapplication.ui.theme.pizzaSmall
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PizzaSlider(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pizzas: List<Painter>,
    pizzaSize: PizzaSize,
    content: @Composable (page: Int) -> Unit
) {
    val pizzaSizeState: Dp by animateDpAsState(
        targetValue = when (pizzaSize) {
            PizzaSize.Small -> pizzaSmall
            PizzaSize.Medium -> pizzaMedium
            PizzaSize.Large -> pizzaLarge
        }, animationSpec = spring()
    )

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        itemSpacing = (-70).dp,

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
//            Image(
//                painter = pizzas[index],
//                contentDescription = null,
//                modifier = Modifier
//                    .size(pizzaSizeState)
//                    .padding(horizontal = space40)
//            )

            content(index)
        }
    }
}
