package com.theChance.myapplication.ui.home

import com.pancake.brainburst.ui.base.BaseUiState

data class HomeUIState(
    val pizzaSize: PizzaSize = PizzaSize.Small
): BaseUiState
