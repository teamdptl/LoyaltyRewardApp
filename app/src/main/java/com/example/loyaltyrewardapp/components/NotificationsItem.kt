package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.data.model.Transaction

@Composable
fun NotificationsItem(
    item: Transaction,
//    titleProvider: (T) -> String,
//    timeProvider: (T) -> String,
//    descriptionProvider: (T) -> String,
//    pictureUrlProvider: (T) -> Int // Provider for picture URL
) {
    CardList() {
        Row(Modifier.clickable { }) {
            SquareImage(
                item = item,
                pictureUrlProperty = item.shop.logo,
                modifier = Modifier.padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .offset(y=6.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(
                        text = item.shop.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Text(
                        text =item.created_at,
                        maxLines = 1,
                        fontSize = 8.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = item.reason, maxLines = 1, fontSize = 12.sp, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
