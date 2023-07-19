package com.theChance.myapplication.ui.home

import com.pancake.brainburst.ui.base.BaseUiState

data class HomeUIState(
    val pizzaSize: PizzaSize = PizzaSize.Small,
    val pizzaToppings: PizzaToppingsUIState = PizzaToppingsUIState(),
    //val pizzaSize: PizzaSizeUIState
) : BaseUiState

//data class PizzaSizeUIState(
//    val pizzaSize: PizzaSize = PizzaSize.Small,
//    val pizzaIndex: Int = 0)

data class PizzaToppingsUIState(
    val isMushroomSlicesAdded: Boolean = false,
    val isOnionSlicesAdded: Boolean = false,
    val isSaysageSlicesAdded: Boolean = false,
    val isBasilSlicesAdded: Boolean = false,
)