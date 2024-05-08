package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loyaltyrewardapp.data.model.Coupon

class AdminCURCouponViewModel : ViewModel() {
    private val _screenState = mutableStateOf<String>("R")
    val screenState get() = _screenState

    private val _coupon = mutableStateOf<Coupon?>(null)
    val coupon get() = _coupon

    init {
        getDetailCoupon("C")
    }

    fun getDetailCoupon(typeAction: String){
        _screenState.value = typeAction
        //Lấy dữ liệu từ api
        //Tạm thời tạo data giả
        _coupon.value = Coupon("haha", "Miễn phí thay nhớt", "nsovqhovn avijqjvn oonjjqn onnk vq", 20, 6, "", true)
    }

    fun updateCoupon(coupon: Coupon) {
        _coupon.value = coupon
    }

    fun updateCouponName(name: String) {
        _coupon.value = _coupon.value?.copy(couponName = name)
    }

    fun updateDescription(description: String) {
        _coupon.value = _coupon.value?.copy(description = description)
    }

    fun updatePoint(point: Int) {
        _coupon.value = _coupon.value?.copy(point = point)
    }

    fun updateTimeExpire(timeExpire: Int) {
        _coupon.value = _coupon.value?.copy(timeExpire = timeExpire)
    }

    fun updateImageUri(uri: String) {
        _coupon.value = _coupon.value?.copy(imageUri = uri)
    }

    fun updateIsActive(isActive: Boolean) {
        _coupon.value = _coupon.value?.copy(isActive = isActive)
    }

    fun updateScreenState(state: String) {
        _screenState.value = state
    }

}