package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.CouponResponse
import kotlinx.coroutines.launch

class CouponUserQRViewModel  : ViewModel() {
    val couponUserQR = mutableStateOf<CouponResponse?>(CouponResponse())
    fun getCouponUserQR(couponId: String) {
        viewModelScope.launch {
            val data = ApiSingleton.getApiService().getCouponUser(couponId)
            couponUserQR.value = data
        }
    }

}