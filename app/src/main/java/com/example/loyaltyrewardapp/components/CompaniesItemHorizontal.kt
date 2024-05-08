package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.navigation.Screens
import com.example.loyaltyrewardapp.ui.theme.GrayMap


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any> CompaniesItem(
    item: T,
    nameProvider: (T) -> String,
    addressProvider: (T) -> String,
    pictureUrlProvider: (T) -> String,
    navController: NavController,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.padding(6.dp).width(150.dp).clickable {
        onClick()
    }) {
        SquareOnlineImage(
            url = pictureUrlProvider(item),
        )
        Column(
        ) {
            Text(
                text = nameProvider(item),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 10.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(verticalAlignment = Alignment.CenterVertically,   modifier = Modifier.padding(top = 5.dp)) {
//                Icon(
//                    Icons.Filled.LocationOn,
//                    contentDescription = "",
//                    modifier = Modifier.size(18.dp),
//                    tint = Color.Gray
//                )
                Text(
                    text = addressProvider(item),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    color = GrayMap,
                    overflow = TextOverflow.Ellipsis,
                )

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CompaniesItemPreview(){
//    CompaniesItem(
//        item = "Địa chi",
//        nameProvider = { it },
//        addressProvider = { it },
//        pictureUrlProvider = { R.drawable.promotion })
//}