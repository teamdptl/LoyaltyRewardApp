package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.data.model.UserPoint

@Composable
fun ListPointItem(
    item: UserPoint,
) {
    CardList(modifier = Modifier.clip(shape = CircleShape)) {
        Row(
            Modifier
                .background(Color.Black.copy(alpha = 0.2f))
                .clickable { }) {
            SquareImageListPoint(
                item = item,
                pictureUrlProperty = item.shop.logo,
                modifier = Modifier
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 2.dp)
                    .weight(0.7f)
                    .align(Alignment.CenterVertically),

            ) {
                Text(
                    text = item.shop.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Text(
                    text = item.shop.address,
                    maxLines = 1,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Text(
                text = " ${item.points}đ",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.3f)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFFFD700),
                        shape = CircleShape
                    ).align(Alignment.CenterVertically),
            )


        }
    }
}
