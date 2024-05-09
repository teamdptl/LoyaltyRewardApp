package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loyaltyrewardapp.data.model.CouponResponse
//import com.example.loyaltyrewardapp.data.model.DetailShopCoupon

class CouponUserQR  : ViewModel() {
    val coupon = mutableStateOf<CouponResponse?>(CouponResponse())


}