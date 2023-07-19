package com.theChance.myapplication.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.theChance.myapplication.R
import com.theChance.myapplication.ui.theme.normalButtonHeight
import com.theChance.myapplication.ui.theme.space16


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaOrderSizeButtons(
    modifier: Modifier = Modifier,
    onLargeSizeClicked: () -> Unit,
    onMediumSizeClicked: () -> Unit,
    onSmallSizeClicked: () -> Unit,
    ) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = space16),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AssistChip(
            onClick = { onSmallSizeClicked() },
            shape = CircleShape,
            label = { Text(text = stringResource(R.string.size_small)) },
            modifier = Modifier.height(normalButtonHeight),

            )

        AssistChip(onClick = { onMediumSizeClicked() },
            shape = CircleShape,
            label = { Text(text = stringResource(R.string.size_medium)) },
            modifier = Modifier.height(normalButtonHeight)
        )

        AssistChip(onClick = { onLargeSizeClicked() },
            shape = CircleShape,
            label = { Text(text = stringResource(R.string.size_large)) },
            modifier = Modifier.height(normalButtonHeight)

        )


    }


}

@Preview
@Composable
fun PreviewPizzaOrderSizeButtons() {
    PizzaOrderSizeButtons(onLargeSizeClicked = {},
        onMediumSizeClicked = {},
        onSmallSizeClicked = {})
}
