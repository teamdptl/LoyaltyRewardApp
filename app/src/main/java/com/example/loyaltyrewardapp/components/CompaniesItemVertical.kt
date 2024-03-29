package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.screens.ui.theme.GrayMap

@Composable
fun <T : Any> CompaniesItemVertical(
    item: T,
    nameProvider: (T) -> String,
    addressProvider: (T) -> String,
    pictureUrlProvider: (T) -> Int
) {
    CardList(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        Row() {
            SquareImage(
                item = item,
                pictureUrlProperty = pictureUrlProvider,
                modifier = Modifier
                    .weight(2.5f),
                size = 90.dp
            )
            Column(
                modifier = Modifier.weight(7.5f)
            ) {
                Text(
                    text = nameProvider(item),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically,   modifier = Modifier.padding(top = 10.dp)) {
//                    Icon(
//                        Icons.Filled.LocationOn,
//                        contentDescription = "",
//                        modifier = Modifier.size(18.dp)
//                    )
                    Text(
                        text = addressProvider(item),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        color = GrayMap
                    )
                }
            }
        }
    }
}