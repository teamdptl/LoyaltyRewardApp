package com.example.loyaltyrewardapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.GiftCardItem
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyaltyrewardapp.data.viewmodel.CouponUserViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.CompaniesItem
import com.example.loyaltyrewardapp.navigation.Screens


@Composable
fun ListDiscountCoupon(navController: NavController = rememberNavController(), viewModel: CouponUserViewModel = CouponUserViewModel()){

    val couponList by remember { viewModel.couponListResponse }

    LaunchedEffect(null) {
        viewModel.fetchCouponList()
        Log.d("Loading", "Dang load du lieu coupon")
    }

    MainBackgroundScreen(title = "Khuyến mãi",  navController = navController) {
        LazyColumn(
            modifier = Modifier
                .background(Color(0xC6E6E3E3))
                .padding(top = 10.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            items(
                items = couponList,
                itemContent = {
                    GiftCardItem(
                        item = it,
                        onClick = {
                            navController.navigate(Screens.DetailCouponScreen.name + "/${it._id}")
                        }
                    )
                }
            )

        }
    }
}


@Preview
@Composable
fun RenderListCoupon(){
    ListDiscountCoupon()
}