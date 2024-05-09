package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon
import kotlinx.coroutines.launch

class CouponDetailViewModel : ViewModel(){
    val coupon = mutableStateOf<Coupon?>(Coupon())

    fun getCoupon(id: String){
        viewModelScope.launch {
            val data = ApiSingleton.getApiService().getCouponById(id)
            coupon.value = data
        }
    }
}