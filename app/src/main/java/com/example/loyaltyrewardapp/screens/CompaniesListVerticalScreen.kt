package com.example.loyaltyrewardapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.CompaniesItem
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.ShopProvider
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import com.example.loyaltyrewardapp.navigation.Screens


@Preview
@Composable
fun CompaniesListVerticalScreen(navController: NavController = rememberNavController(), viewModel: UserHomeViewModel = UserHomeViewModel()){
    val shops by remember {
        viewModel.recommendShops
    }

    LaunchedEffect(null) {
        viewModel.fetchRecommendShops(100)
    }
    MainBackgroundScreen("Danh sách các cửa hàng", navController = navController) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),

            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
        ) {
            items(
                items = shops,
                itemContent = {
                    CompaniesItem(
                        item = it,
                        nameProvider = { it.name },
                        addressProvider = { it.address },
                        pictureUrlProvider = { it.logo },
                        navController = navController,
                        onClick = {
                            navController.navigate(Screens.DetailShopScreen.name + "/${it._id}")
                        }
                    )
                }
            )
        }
    }
}