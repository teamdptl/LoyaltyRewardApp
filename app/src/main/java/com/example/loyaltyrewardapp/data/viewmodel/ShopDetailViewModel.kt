package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.DetailShop
import com.example.loyaltyrewardapp.data.model.Shop
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ShopDetailViewModel : ViewModel() {
    val shop = mutableStateOf<DetailShop?>(null)

    fun getShopDetail(shopId: String) {
        viewModelScope.launch {
            try {
                val shopDetail = ApiSingleton.getApiService().getShopById(shopId)
                shop.value = shopDetail
            } catch (e: HttpException){
                e.printStackTrace()
            }
        }
    }
}