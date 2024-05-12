package com.example.loyaltyrewardapp.screens.manager

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.HeaderAdmin
import com.example.loyaltyrewardapp.components.ShopOfManagerItem
import com.example.loyaltyrewardapp.components.VisitorsItem
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import com.example.loyaltyrewardapp.data.model.Visited
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import com.example.loyaltyrewardapp.navigation.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
@Preview
fun HomeManagerScreen(navController: NavController = rememberNavController(), homeViewModel: UserHomeViewModel = UserHomeViewModel()){
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser = auth.currentUser


    val user by remember {
        homeViewModel.user
    }

    val visitedManagers by remember {
        homeViewModel.visitedManagers
    }

    val shopDaily by remember {
        homeViewModel.shopDaily
    }

    Log.d("visitedManagers", visitedManagers.toString())

    LaunchedEffect(null) {
        if (user == UserEmptyState){
            homeViewModel.fetchCurrentUser()
            homeViewModel.fetchVisitedManager()
        }
    }


    if (user == UserEmptyState) {
        Log.d("Loading", "Chua co du lieu")
    } else {
        Log.d("Loading", "Da load xong du lieu $user")
        Column() {
            if (firebaseUser != null) {
                shopDaily?.let { HeaderAdmin(firebaseUser, user, it, visitedManagers) }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.padding(start = 22.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cửa hàng sở hữu",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            ShopOfManagerItem(user, onClick = {
                navController.navigate(Screens.UpdateShopScreen.name + "/${user.shop?._id}")
            })

            if (visitedManagers != emptyList<Visited>()) {
                Row(
                    modifier = Modifier.padding(start = 22.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Các lượt ghé thăm trong ngày",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        items = visitedManagers,
                        itemContent = {
                            VisitorsItem(it)
                        }
                    )
                }
            }
        }
    }
    }
