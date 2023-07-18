package com.theChance.myapplication.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.theChance.myapplication.R
import com.theChance.myapplication.ui.theme.space16
import com.theChance.myapplication.ui.theme.typography

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    startIcon: Painter = painterResource(id = R.drawable.ic_back_arrow),
    title: String = stringResource(id = R.string.pizza),
    endIcon: Painter = painterResource(id = R.drawable.ic_heart),
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = space16),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = startIcon, contentDescription = null)
        Text(text = title, style = typography.titleLarge)
        Icon(painter = endIcon, contentDescription = null)
    }
}

@Preview
@Composable
private fun ActionBarPreview() {
    ActionBar()
}