package com.example.loyaltyrewardapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.loyaltyrewardapp.components.CompaniesItem
import com.example.loyaltyrewardapp.components.MainHeader
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.data.ShopProvider

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column {
            MainHeader()
            Row(
                modifier = Modifier.padding(start = 22.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

            Text(text = "Danh sách các công ty", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f) )
                TextButton(
                    onClick = {
                    }
                ) {
                    Text(
                        text = "Tất cả",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Icon(
                        Icons.Filled.KeyboardDoubleArrowRight,
                        contentDescription = "",
                    )
                }
            }
            val companies = remember { ShopProvider.homeList }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = companies,
                    itemContent = {
                        CompaniesItem(
                            item = it,
                            nameProvider = { it.title },
                            addressProvider = { it.address },
                            pictureUrlProvider = { it.pictureUrl }
                        )
                    }
                )
            }


        }




        }

}