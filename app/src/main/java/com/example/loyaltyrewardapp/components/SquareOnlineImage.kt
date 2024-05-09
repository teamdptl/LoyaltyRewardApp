package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.loyaltyrewardapp.R

@Composable
fun SquareOnlineImage(
    url: String,
    modifier: Modifier = Modifier,
    size: Dp = 150.dp,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp) // Hình dạng cắt mặc định
) {
    AsyncImage(
        model = url,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(shape)
    )
}