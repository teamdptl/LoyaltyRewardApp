package com.example.loyaltyrewardapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.GiftCardItem
import com.example.loyaltyrewardapp.components.MainBackgroundScreen


@Composable
fun ListDiscountCoupon(){
    MainBackgroundScreen(title = "Khuyến mãi") {
        LazyColumn(
            modifier = Modifier.background(Color(0xC6E6E3E3))
                .padding(top = 10.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            items(10){
                index -> GiftCardItem()
            }
        }
    }
}


@Preview
@Composable
fun RenderListCoupon(){
    ListDiscountCoupon()
}