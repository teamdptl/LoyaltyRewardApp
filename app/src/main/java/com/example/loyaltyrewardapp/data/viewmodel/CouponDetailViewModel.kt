package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CouponDetailViewModel : ViewModel(){
    val coupon = mutableStateOf<Coupon?>(Coupon())
    val successMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)
    val errorMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)

    fun getCoupon(id: String){
        viewModelScope.launch {
            val data = ApiSingleton.getApiService().getCouponById(id)
            coupon.value = data
        }
    }

    fun exchangeCoupon(couponId: String){
        viewModelScope.launch {
            try {
                val response = ApiSingleton.getApiService().exchangeCoupon(couponId)
                successMessage.value = response
                Log.d("exchangeCoupon", "exchangeCoupon: $response")
            } catch (e: HttpException){
                if (e.code() in 400..499){
                    errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
                    Log.d("exchangeCoupon", "errorExchange: ${errorMessage.value}")
                }
            }
        }
    }

}