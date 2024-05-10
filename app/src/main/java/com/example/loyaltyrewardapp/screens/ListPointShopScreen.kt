package com.example.loyaltyrewardapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.GiftCardItem
import com.example.loyaltyrewardapp.components.ListPointItem
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.viewmodel.CouponUserViewModel
import com.example.loyaltyrewardapp.data.viewmodel.ListPointViewModel


@Composable
fun ListPointShop( navController: NavController = rememberNavController(),viewModel: ListPointViewModel = ListPointViewModel()){

    val pointList by remember { viewModel.listpoints }

    LaunchedEffect(null) {
        viewModel.getListPoint()
        Log.d("Loading", "Dang load du lieu danh điểm")
    }

    MainBackgroundScreen(title = "Danh sách cửa hàng tích điểm  ",navController = navController) {
        LazyColumn(
            modifier = Modifier
                .background(Color(0xC6E6E3E3))
                .padding(top = 10.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            items(
                items = pointList,
                itemContent = {
                    ListPointItem(
                        item = it,
                    )
                }
            )

        }
    }
}


@Preview
@Composable
fun RenderListPont(){
    ListPointShop()
}