package com.theChance.myapplication.ui.home

import com.pancake.brainburst.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update

class HomeViewModel: BaseViewModel<HomeUIState>(HomeUIState()){

    fun onPizzaSizeChange(newSize: PizzaSize){
        _state.update { it.copy(pizzaSize = newSize) }
//        _state.update { it.copy(pizzaSizeUIState = PizzaSizeUIState(pizzaSize = newSize, pizzaIndex = pizzaIndex)) }
    }

    fun onAddIngredient(ingredientState: Boolean){
        _state.update { it.copy(pizzaToppings = PizzaToppingsUIState(isMushroomSlicesAdded = ingredientState)) }
    }
}