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
import com.theChance.myapplication.ui.composables.NormalIconButton
import com.theChance.myapplication.ui.composables.PizzaOrderSizeButtons
import com.theChance.myapplication.ui.composables.PizzaSlider
import com.theChance.myapplication.ui.composables.PizzaToppingsRow
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
    val pizzas = listOf<Pair<Painter, PizzaSize>>(
        Pair(painterResource(id = R.drawable.bread_1), PizzaSize.Small),
        Pair(painterResource(id = R.drawable.bread_2), PizzaSize.Small),
        Pair(painterResource(id = R.drawable.bread_3), PizzaSize.Small),
        Pair(painterResource(id = R.drawable.bread_4), PizzaSize.Small),
        Pair(painterResource(id = R.drawable.bread_5), PizzaSize.Small),
    )
    val pizzaToppings = listOf(
        painterResource(id = R.drawable.basil_8),
        painterResource(id = R.drawable.broccoli_5),
        painterResource(id = R.drawable.mushroom_3),
        painterResource(id = R.drawable.onion_3),
        painterResource(id = R.drawable.sausage_1),

        )
    val pagerState = rememberPagerState(pizzas.size)

    MainContent(
        state = state,
        pagerState = pagerState,
        pizzas = pizzas.map { it.first },
        pizzaToppings = pizzaToppings,
        onAddIngredient = { viewModel.onAddIngredient(it) },
        onSmallSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Small) },
        onLargeSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Large) },
        onMediumSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Medium) },
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainContent(
    state: HomeUIState,
    pagerState: PagerState,
    pizzas: List<Painter>,
    pizzaToppings: List<Painter>,
    onAddIngredient: (ingredientState: Boolean) -> Unit,
    onLargeSizeClicked: () -> Unit,
    onMediumSizeClicked: () -> Unit,
    onSmallSizeClicked: () -> Unit,
) {
    //val selectedSize by rememberUpdatedState(newValue = state.pizzaSize)


    ConstraintLayout {
        val (actionBar, imagePlate, imagePizza, sliderPizza, textPrice, buttonsPizzaSize,
            textCustomize, pizzaToppingsRow, buttonAddToCart, imagePizzaToppings) = createRefs()

        ActionBar(modifier = Modifier.constrainAs(actionBar) {
            top.linkTo(parent.top, space16)
        })

        Image(painter = painterResource(R.drawable.plate),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = space40)
                .constrainAs(imagePlate) {
                    top.linkTo(actionBar.bottom, space24)
                })



        PizzaSlider(pagerState = pagerState,
            pizzas = pizzas,
            pizzaSize = state.pizzaSize,
            modifier = Modifier.constrainAs(sliderPizza) {
                top.linkTo(imagePlate.top)
                bottom.linkTo(imagePlate.bottom)
                start.linkTo(imagePlate.start)
                end.linkTo(imagePlate.end)
            })

        if (state.pizzaToppings.isMushroomSlicesAdded) {
            Image(
                painter = painterResource(id = R.drawable.mushroom_slices),
                contentDescription = null,
                modifier = Modifier.constrainAs(imagePizzaToppings) {
                    top.linkTo(sliderPizza.top)
                    bottom.linkTo(sliderPizza.bottom)
                    start.linkTo(sliderPizza.start)
                    end.linkTo(sliderPizza.end)
                }
            )
        }

        Text(text = "$17",
            style = typography.titleLarge,
            modifier = Modifier.constrainAs(textPrice) {
                top.linkTo(imagePlate.bottom, space24)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        PizzaOrderSizeButtons(onLargeSizeClicked = { onLargeSizeClicked() },
            onMediumSizeClicked = { onMediumSizeClicked() },
            onSmallSizeClicked = { onSmallSizeClicked() },
            modifier = Modifier.constrainAs(buttonsPizzaSize) {
                top.linkTo(textPrice.bottom, space24)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            })

        Text(text = stringResource(R.string.customize),
            style = typography.titleSmall,
            modifier = Modifier.constrainAs(textCustomize) {
                top.linkTo(buttonsPizzaSize.bottom, space32)
                start.linkTo(parent.start, space16)
            })

        PizzaToppingsRow(
            pizzaToppings = pizzaToppings,
            onClick = { onAddIngredient(!state.pizzaToppings.isMushroomSlicesAdded) },
            modifier = Modifier.constrainAs(pizzaToppingsRow) {
                top.linkTo(textCustomize.bottom, space24)
                start.linkTo(parent.start, space16)
            }

        )

        NormalIconButton(onClick = {}, modifier = Modifier.constrainAs(buttonAddToCart) {
            bottom.linkTo(parent.bottom, space24)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        })

    }
}


//@OptIn(ExperimentalPagerApi::class)
//@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
//@Composable
//fun MainScreenPreview() {
//    val viewModel: HomeViewModel = hiltViewModel()
//    val state by viewModel.state.collectAsState()
//    val pizzas = listOf(
//        painterResource(id = R.drawable.bread_1),
//        painterResource(id = R.drawable.bread_2),
//        painterResource(id = R.drawable.bread_3),
//        painterResource(id = R.drawable.bread_4),
//        painterResource(id = R.drawable.bread_5),
//    )
//    val pagerState = rememberPagerState(pizzas.size)
//
//    MainContent(
//        state = state,
//        onLargeSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Large) },
//        onMediumSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Medium) },
//        onSmallSizeClicked = { viewModel.onPizzaSizeChange(newSize = PizzaSize.Small) },
//        pagerState = pagerState,
//        pizzas = pizzas
//    )
//}