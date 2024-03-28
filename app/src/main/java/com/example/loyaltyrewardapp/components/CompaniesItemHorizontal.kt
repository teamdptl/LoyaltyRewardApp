package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.screens.ui.theme.GrayMap


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any> CompaniesItem(
    item: T,
    nameProvider: (T) -> String,
    addressProvider: (T) -> String,
    pictureUrlProvider: (T) -> Int // Provider for picture URL
) {
    CardList(
        modifier = Modifier
            .width(180.dp)
            .height(250.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SquareImage(
                item = item,
                pictureUrlProperty = pictureUrlProvider,
                size = 160.dp,
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = nameProvider(item),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically,   modifier = Modifier.padding(top = 5.dp)) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "",
                        modifier = Modifier.size(18.dp)
                    )
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