package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.reflect.KProperty1

@Composable
fun <T> SquareImage(
    item: T,
    pictureUrlProperty: KProperty1<T, Int>,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = item.run(pictureUrlProperty)),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}