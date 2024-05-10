package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.loyaltyrewardapp.data.model.Transaction
import com.example.loyaltyrewardapp.data.model.UserPoint

@Composable
fun SquareImage(
    item: Transaction,
    pictureUrlProperty: String,
    modifier: Modifier = Modifier,
    size: Dp = 84.dp,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp) // Hình dạng cắt mặc định
) {
    var painter = rememberAsyncImagePainter(model = pictureUrlProperty)
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(shape)
    )
}
@Composable
fun SquareImageListPoint(
    item: UserPoint,
    pictureUrlProperty: String,
    modifier: Modifier = Modifier,
    size: Dp = 84.dp,
    shape: RoundedCornerShape = CircleShape // Hình dạng cắt mặc định
) {
    var painter = rememberAsyncImagePainter(model = pictureUrlProperty)
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size)
            .clip(shape)
    )
}