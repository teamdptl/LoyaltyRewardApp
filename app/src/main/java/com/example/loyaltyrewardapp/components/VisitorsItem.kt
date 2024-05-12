package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.ShopDaily
import com.example.loyaltyrewardapp.data.model.Visited


@Composable
fun VisitorsItem(
    visitedManagers: Visited
){
    CardList() {
        Row(Modifier.clickable { }) {
//            SquareImage(
//                item = item,
//                pictureUrlProperty = item.shop.logo,
//                modifier = Modifier.padding(8.dp)
//            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                SquareOnlineImage(
                    url = visitedManagers.photo,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(5.dp)
                        .clip(CircleShape),
                    )
            }

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(
                        text = "Khách: ${visitedManagers.name}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = visitedManagers.created_at,
                        modifier = Modifier.weight(3f),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "+ ${visitedManagers.point}",
                        modifier = Modifier.weight(1f),
                        fontSize = 15.sp,
                        textAlign = TextAlign.End,
                        color = Color.Green
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = visitedManagers.reason, maxLines = 1, fontSize = 12.sp, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}