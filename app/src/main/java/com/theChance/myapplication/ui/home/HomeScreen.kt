package com.theChance.myapplication.ui.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.theChance.myapplication.R
import com.theChance.myapplication.ui.composables.ActionBar
import com.theChance.myapplication.ui.composables.PizzaSlider
import com.theChance.myapplication.ui.theme.ButtonPrimary
import com.theChance.myapplication.ui.theme.LayoutBackground
import com.theChance.myapplication.ui.theme.normalButtonHeight
import com.theChance.myapplication.ui.theme.pizzaLarge
import com.theChance.myapplication.ui.theme.pizzaMedium
import com.theChance.myapplication.ui.theme.pizzaSmall
import com.theChance.myapplication.ui.theme.radius10
import com.theChance.myapplication.ui.theme.space16
import com.theChance.myapplication.ui.theme.space24
import com.theChance.myapplication.ui.theme.space32
import com.theChance.myapplication.ui.theme.space40
import com.theChance.myapplication.ui.theme.space8
import com.theChance.myapplication.ui.theme.textSize16
import com.theChance.myapplication.ui.theme.typography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel()

) {
    val state by viewModel.state.collectAsState()
    val pizzas = listOf(
        painterResource(id = R.drawable.bread_1),
        painterResource(id = R.drawable.bread_2),
        painterResource(id = R.drawable.bread_3),
        painterResource(id = R.drawable.bread_4),
        painterResource(id = R.drawable.bread_5),
    )
    val pagerState = rememberPagerState(pizzas.size)

    MainContent(
        state = state,
        onLargeSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Large) },
        onMediumSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Medium) },
        onSmallSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Small) },
        pagerState = pagerState,
        pizzas = pizzas
    )
}

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@Composable
private fun MainContent(
    state: HomeUIState,
    pagerState: PagerState,
    pizzas: List<Painter>,
    onLargeSizeClicked: () -> Unit,
    onMediumSizeClicked: () -> Unit,
    onSmallSizeClicked: () -> Unit,
) {
    val selectedSize by rememberUpdatedState(newValue = state.pizzaSize)


    ConstraintLayout {
        val (actionBar, imagePlate, imagePizza, sliderPizza, textPrice, buttonsPizzaSize, textCustomize, buttonAddToCart) = createRefs()

        ActionBar(modifier = Modifier.constrainAs(actionBar) {
            top.linkTo(parent.top, space16)
        })

        Image(
            painter = painterResource(R.drawable.plate),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = space40)
                .constrainAs(imagePlate) {
                    top.linkTo(actionBar.bottom, space24)
                }
        )

        val pizzaSize: Dp by animateDpAsState(
            targetValue = when (state.pizzaSize) {
                PizzaSize.Small -> pizzaSmall
                PizzaSize.Medium -> pizzaMedium
                PizzaSize.Large -> pizzaLarge
            },
            animationSpec = spring()
        )

        PizzaSlider(
            pagerState = pagerState,
            pizzas = pizzas,
            pizzaSize = pizzaSize,
            modifier = Modifier.constrainAs(sliderPizza) {
                top.linkTo(imagePlate.top)
                bottom.linkTo(imagePlate.bottom)
                start.linkTo(imagePlate.start)
                end.linkTo(imagePlate.end)
            })


        Text(
            text = "$17",
            style = typography.titleLarge,
            modifier = Modifier.constrainAs(textPrice) {
                top.linkTo(imagePlate.bottom, space24)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = space16)
                .constrainAs(buttonsPizzaSize) {
                    top.linkTo(textPrice.bottom, space24)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AssistChip(
                onClick = { onSmallSizeClicked() },
                shape = CircleShape,
                label = { Text(text = stringResource(R.string.sizeSmall)) },
                modifier = Modifier.height(normalButtonHeight),

                )

            AssistChip(
                onClick = { onMediumSizeClicked() },
                shape = CircleShape,
                label = { Text(text = stringResource(R.string.size_medium)) },
                modifier = Modifier.height(normalButtonHeight)
            )

            AssistChip(
                onClick = { onLargeSizeClicked() },
                shape = CircleShape,
                label = { Text(text = stringResource(R.string.size_large)) },
                modifier = Modifier.height(normalButtonHeight)

            )

        }

        Text(
            text = stringResource(R.string.customize),
            style = typography.titleSmall,
            modifier = Modifier.constrainAs(textCustomize) {
                top.linkTo(buttonsPizzaSize.bottom, space32)
                start.linkTo(parent.start, space16)
            })

        Button(onClick = { },
            shape = RoundedCornerShape(radius10),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonPrimary,
                contentColor = LayoutBackground
            ),
            modifier = Modifier
                .height(normalButtonHeight)
                .constrainAs(buttonAddToCart) {
                    bottom.linkTo(parent.bottom, space24)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(R.string.add_to_cart),
                    style = typography.titleLarge,
                    color = LayoutBackground,
                    fontSize = textSize16,
                    modifier = Modifier.padding(start = space8)
                )
            }
        }


//        PizzaOrderSizeButtons(
//            onLargeSizeClicked = { onLargeSizeClicked() },
//            onMediumSizeClicked = { onMediumSizeClicked() },
//            onSmallSizeClicked = { onSmallSizeClicked() },
//            modifier = Modifier.constrainAs(buttonsPizzaSize){
//                top.linkTo(imagePlate.bottom)
//                end.linkTo(parent.end)
//                start.linkTo(parent.start)
//            }
//        )


    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MainScreenPreview() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val pizzas = listOf(
        painterResource(id = R.drawable.bread_1),
        painterResource(id = R.drawable.bread_2),
        painterResource(id = R.drawable.bread_3),
        painterResource(id = R.drawable.bread_4),
        painterResource(id = R.drawable.bread_5),
    )
    val pagerState = rememberPagerState(pizzas.size)

    MainContent(
        state = state,
        onLargeSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Large) },
        onMediumSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Medium) },
        onSmallSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Small) },
        pagerState = pagerState,
        pizzas = pizzas
    )
}