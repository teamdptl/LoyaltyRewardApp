package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon

class AdminCURCouponViewModel : ViewModel() {
    private val _screenState = mutableStateOf<String>("R")
    val screenState get() = _screenState

    private val _coupon = mutableStateOf<Coupon?>(null)
    val coupon get() = _coupon


    suspend fun getDetailCoupon(typeAction: String, id: String){
        _screenState.value = typeAction
        //Lấy dữ liệu từ api
        val coupon = ApiSingleton.getApiService().getCouponById(id)
        _coupon.value = coupon
    }

    fun updateCoupon(coupon: Coupon) {
        _coupon.value = coupon
    }

    fun updateCouponName(name: String) {
        _coupon.value = _coupon.value?.copy(name = name)
    }

    fun updateDescription(description: String) {
        _coupon.value = _coupon.value?.copy(description = description)
    }

    fun updatePoint(point: Int) {
        _coupon.value = _coupon.value?.copy(require_point = point)
    }

    fun updateTimeExpire(timeExpire: Int) {
        _coupon.value = _coupon.value?.copy(expired_after = timeExpire)
    }

    fun updateImageUri(uri: String) {
        _coupon.value = _coupon.value?.copy(icon = uri)
    }

    fun updateIsActive(isActive: Boolean) {
        _coupon.value = _coupon.value?.copy(is_active = isActive)
    }

    fun updateScreenState(state: String) {
        _screenState.value = state
    }

}