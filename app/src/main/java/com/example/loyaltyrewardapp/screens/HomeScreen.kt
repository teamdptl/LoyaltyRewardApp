package com.example.loyaltyrewardapp.screens

//import androidx.compose.material.icons.Icons
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.CompaniesItem
import com.example.loyaltyrewardapp.components.MainUserHeader
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.Shop
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import com.example.loyaltyrewardapp.navigation.Screens
import com.example.loyaltyrewardapp.ui.theme.MainColor
//import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

//import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(navController: NavController = rememberNavController(), homeViewModel: UserHomeViewModel = UserHomeViewModel()) {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser = auth.currentUser

    val user by remember {
        homeViewModel.user
    }

    val recommendShops by remember {
        homeViewModel.recommendShops
    }

    val visitedShops by remember {
        homeViewModel.visitedShops
    }

    val availableCoupons by remember {
        homeViewModel.availableCoupons
    }

    LaunchedEffect(null) {
        if (user == UserEmptyState){
            homeViewModel.fetchCurrentUser()
            homeViewModel.fetchRecommendShops()
            homeViewModel.fetchVisitedShops()
            homeViewModel.fetchAvailableVoucher()
        }
    }

    if (user == UserEmptyState) {
        Log.d("Loading", "Chua co du lieu")
    } else {
        Log.d("Loading", "Da load xong du lieu $user")
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            if (firebaseUser != null) {
                MainUserHeader(navController, firebaseUser, user)
            }
            // Bỏ đoạn này làm cái composable để tái sử dụng
            Row(
                modifier = Modifier.padding(start = 22.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Danh sách các cửa hàng",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screens.ShopVerticalScreen.name)
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

            if (recommendShops == emptyList<Shop>()){
                Log.d("Loading", "Chua co du lieu")
            } else {
                Log.d("Loading", "Da load xong du lieu ${recommendShops[0].logo}")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        items = recommendShops,
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


            Row(
                modifier = Modifier.padding(start = 22.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Đã ghé thăm",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
//                TextButton(
//                    onClick = {
//                        navController.navigate(Screens.ShopVerticalScreen.name)
//                    }
//                ) {
//                    Text(
//                        text = "Xem thêm",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MainColor
//                    )
//                    Icon(
//                        Icons.Filled.MoreVert,
//                        contentDescription = "",
//                        modifier = Modifier.size(20.dp),
//                        tint = MainColor
//                    )
//                }
            }

            if (visitedShops == emptyList<Shop>()){
                Log.d("Loading", "Chua co du lieu")
            } else {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        items = visitedShops,
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

            if (availableCoupons != emptyList<Coupon>()){
                Row(
                    modifier = Modifier.padding(start = 22.dp, top = 10.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Ưu đãi có thể đổi",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(
                        onClick = {
                            // TDOO
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

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        items = availableCoupons,
                        itemContent = {
                            InfoRewardCard(
                                name = it.name,
                                description = it.description,
                                point = it.require_point,
                                url = it.icon,
                                onClick = {
                                    navController.navigate(Screens.DetailCouponScreen.name + "/${it._id}")
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}