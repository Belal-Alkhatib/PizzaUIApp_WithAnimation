package com.theChance.myapplication.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.theChance.myapplication.R
import com.theChance.myapplication.ui.theme.ButtonPrimary
import com.theChance.myapplication.ui.theme.LayoutBackground
import com.theChance.myapplication.ui.theme.normalButtonHeight
import com.theChance.myapplication.ui.theme.radius10
import com.theChance.myapplication.ui.theme.space24
import com.theChance.myapplication.ui.theme.space8
import com.theChance.myapplication.ui.theme.textSize16
import com.theChance.myapplication.ui.theme.typography

@Composable
fun NormalIconButton(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.add_to_cart),
    onClick: () -> Unit,
    @DrawableRes iconId: Int = R.drawable.ic_cart
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(radius10),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonPrimary,
            contentColor = LayoutBackground
        ),
        modifier = modifier
            .height(normalButtonHeight)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
            )
            Text(
                text = title,
                style = typography.titleLarge,
                color = LayoutBackground,
                fontSize = textSize16,
                modifier = Modifier.padding(start = space8)
            )
        }
    }
}