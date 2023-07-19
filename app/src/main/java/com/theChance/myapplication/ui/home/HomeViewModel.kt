package com.theChance.myapplication.ui.home

import com.pancake.brainburst.ui.base.BaseViewModel
import kotlinx.coroutines.flow.update

class HomeViewModel: BaseViewModel<HomeUIState>(HomeUIState()){

    fun onPizzaSizeChange(newSize: PizzaSize){
        _state.update { it.copy(pizzaSize = newSize) }
//        _state.update { it.copy(pizzaSizeUIState = PizzaSizeUIState(pizzaSize = newSize, pizzaIndex = pizzaIndex)) }
    }

    fun onAddIngredient(pizzaToppings: PizzaToppings){
        when(pizzaToppings){
            PizzaToppings.Basil -> _state.update { it.copy(pizzaToppings = PizzaToppingsUIState(isBasilSlicesAdded = !_state.value.pizzaToppings.isBasilSlicesAdded )) }
            PizzaToppings.Mushroom -> _state.update { it.copy(pizzaToppings = PizzaToppingsUIState(isMushroomSlicesAdded = !_state.value.pizzaToppings.isMushroomSlicesAdded )) }
            PizzaToppings.Sausage -> _state.update { it.copy(pizzaToppings = PizzaToppingsUIState(isSaysageSlicesAdded = !_state.value.pizzaToppings.isSaysageSlicesAdded )) }
            PizzaToppings.Onion -> _state.update { it.copy(pizzaToppings = PizzaToppingsUIState(isOnionSlicesAdded = !_state.value.pizzaToppings.isOnionSlicesAdded )) }
            PizzaToppings.Broccoli -> _state.update { it.copy(pizzaToppings = PizzaToppingsUIState(isBroccoliSlicesAdded = !_state.value.pizzaToppings.isBroccoliSlicesAdded )) }
        }

    }
}