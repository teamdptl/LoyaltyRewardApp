package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T : Any> HistoryItem(
    item: T,
    titleProvider: (T) -> String,
    timeProvider: (T) -> String,
    descriptionProvider: (T) -> String,
    pointProvider: (T) -> String,
    pictureUrlProvider: (T) -> Int
) {
    CardList() {
        Row(Modifier.clickable { }) {
            SquareImage(
                item = item,
                pictureUrlProperty = pictureUrlProvider,
                modifier = Modifier
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 2.dp)
                    .weight(0.7f)
                    .offset(y=10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = titleProvider(item),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                    Text(
                        text = timeProvider(item),
                        maxLines = 1,
                        fontSize = 12.sp,
                    )

                Text(
                    text = descriptionProvider(item),
                    maxLines = 2,
                    fontSize = 12.sp,
                )
            }
            Text(
                text = "+ ${pointProvider(item)}đ",
                color = Color(0xFF11942C),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp) .weight(0.3f)  .fillMaxWidth()   .offset(y=30.dp),
            )
        }
    }
}
