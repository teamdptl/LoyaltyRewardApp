package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.Shop
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import kotlinx.coroutines.launch

class UserHomeViewModel: ViewModel() {
    val user: MutableState<User> = mutableStateOf(UserEmptyState)
    val recommendShops: MutableState<List<Shop>> = mutableStateOf(emptyList<Shop>())
    val visitedShops: MutableState<List<Shop>> = mutableStateOf(emptyList<Shop>())
    val availableCoupons: MutableState<List<Coupon>> = mutableStateOf(emptyList<Coupon>())

    fun fetchCurrentUser() {
        viewModelScope.launch {
            val currentUser = ApiSingleton.getApiService().getCurrentUser()
            user.value = currentUser
            Log.d("Loading", "fetchCurrentUser: ${user.value}")
        }
    }

    fun fetchRecommendShops(limit: Int = 10) {
        viewModelScope.launch {
            val shops = ApiSingleton.getApiService().getRecommendedShops(limit)
            recommendShops.value = shops
        }
    }

    fun fetchVisitedShops() {
        viewModelScope.launch {
            val shops = ApiSingleton.getApiService().getVisitedShops()
            visitedShops.value = shops
        }
    }

    fun fetchAvailableVoucher() {
        viewModelScope.launch {
            val vouchers = ApiSingleton.getApiService().getAvailableCoupons()
            availableCoupons.value = vouchers
        }
    }
}