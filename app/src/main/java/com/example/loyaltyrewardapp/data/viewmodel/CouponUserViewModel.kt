package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.UserCouponResponse
import kotlinx.coroutines.launch

class CouponUserViewModel : ViewModel() {
    var couponListResponse: MutableState<List<UserCouponResponse>> = mutableStateOf(emptyList<UserCouponResponse>())

    var errorMessage: String by mutableStateOf("")
    fun fetchCouponList() {
        viewModelScope.launch {
            try {
                val couponList = ApiSingleton.getApiService().getCoupons()
                couponListResponse.value = couponList
                Log.d("CouponList", couponList.toString())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}