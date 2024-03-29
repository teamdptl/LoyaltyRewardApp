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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> SquareImage(
    item: T,
    pictureUrlProperty: (T) -> Int,
    modifier: Modifier = Modifier,
    size: Dp = 84.dp,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp) // Hình dạng cắt mặc định
) {
    Image(
        painter = painterResource(id = item.run(pictureUrlProperty)),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(shape)
    )
}