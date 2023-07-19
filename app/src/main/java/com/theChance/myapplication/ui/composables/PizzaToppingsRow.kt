package com.theChance.myapplication.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.theChance.myapplication.R
import com.theChance.myapplication.ui.theme.normalButtonHeight
import com.theChance.myapplication.ui.theme.pizzaToppingsHeight
import com.theChance.myapplication.ui.theme.space0
import com.theChance.myapplication.ui.theme.space16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaToppingsRow(modifier: Modifier = Modifier, onClick: () -> Unit, pizzaToppings: List<Painter>) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space16),
        verticalAlignment = Alignment.CenterVertically

    ){
        items(count = pizzaToppings.count()){
            AssistChip(
                onClick = { onClick() },
                label = {},
                shape = CircleShape,
                leadingIcon = {Image(painter = pizzaToppings[it], contentDescription = null, contentScale = ContentScale.Inside)},
                border = AssistChipDefaults.assistChipBorder(borderWidth = space0),
                modifier = Modifier.size(pizzaToppingsHeight),
                )
        }
    }
}