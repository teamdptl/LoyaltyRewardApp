package com.example.loyaltyrewardapp.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.Location
import com.example.loyaltyrewardapp.data.model.Shop

class AdminCURShopViewModel : ViewModel(){
    private val _screenState = mutableStateOf<String>("")
    val screenState get() = _screenState

    private val _shop = mutableStateOf<Shop?>(null)
    val shop get() = _shop

    init {
        getDetailCoupon("C")
    }

    fun getDetailCoupon(typeAction: String){
        _screenState.value = typeAction
        //Lấy dữ liệu từ api
        //Tạm thời tạo data giả
        _shop.value = Shop("haha", "Miễn phí thay nhớt", "nsovqhovn avijqjvn oonjjqn onnk vq", "", "", "", "", Location("", doubleArrayOf(4.4, 5.125).toList()))
    }

    fun updateShopName(name: String){
        _shop.value = _shop.value?.copy(name = name)
    }

    fun updateShopAddress(address: String) {
        _shop.value = _shop.value?.copy(address = address)
    }

    fun updateShopLogo(logo: String) {
        _shop.value = _shop.value?.copy(logo = logo)
    }

    fun updateShopCover(cover: String?) {
        _shop.value = _shop.value?.copy(cover = cover)
    }

    fun updateShopLocation(location: Location?) {
        _shop.value = _shop.value?.copy(location = location)
    }

    fun updateScreenState(state: String) {
        _screenState.value = state
    }
}