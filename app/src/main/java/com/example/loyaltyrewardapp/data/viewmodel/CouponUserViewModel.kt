package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.CouponResponse
import kotlinx.coroutines.launch

class CouponUserViewModel : ViewModel() {
    var couponListResponse: List<CouponResponse> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun fetchCouponList() {
        viewModelScope.launch {
            try {
                val couponList = ApiSingleton.getApiService().getCounpons()
                couponListResponse = couponList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}