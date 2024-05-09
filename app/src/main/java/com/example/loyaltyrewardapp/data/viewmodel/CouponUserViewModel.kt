package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.CouponResponse
import com.example.loyaltyrewardapp.data.model.Shop
import kotlinx.coroutines.launch

class CouponUserViewModel : ViewModel() {
    var couponListResponse: MutableState<List<CouponResponse>> = mutableStateOf(emptyList<CouponResponse>())

    var errorMessage: String by mutableStateOf("")
    fun fetchCouponList() {
        viewModelScope.launch {
            try {
                val couponList = ApiSingleton.getApiService().getCoupons()
                couponListResponse.value = couponList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}