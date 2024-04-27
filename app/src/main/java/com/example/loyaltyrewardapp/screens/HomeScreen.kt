package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.HeaderAdmin
import com.example.loyaltyrewardapp.data.ShopProvider
import com.example.loyaltyrewardapp.ui.theme.MainColor


class HomeScreenActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = androidx.compose.material.MaterialTheme.colors.background
            ) {

                HomeScreen()
            }
        }
    }

}

@Composable
fun HomeScreen(){
    val isAdmin = true

    if(!isAdmin){
        Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
            MainHeader()
            // Bỏ đoạn này làm cái composable để tái sử dụng
            Row(
                modifier = Modifier.padding(start = 22.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Danh sách các công ty",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                TextButton(
                    onClick = {
                    }
                ) {
                    Text(
                        text = "Xem thêm",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MainColor
                    )
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = MainColor
                    )
                }
            }


            val companies = remember { ShopProvider.shopList }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
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
    }else{
        Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
            HeaderAdmin()
            Row(
                modifier = Modifier.padding(start = 22.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cửa hàng sở hữu",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }


    }





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}