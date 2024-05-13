package com.example.loyaltyrewardapp.data.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.CouponRequest
import com.example.loyaltyrewardapp.data.model.Location
import com.example.loyaltyrewardapp.data.model.DetailShop
import com.example.loyaltyrewardapp.data.model.DetailShopService
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import com.example.loyaltyrewardapp.data.model.ResponseUpload
import com.example.loyaltyrewardapp.data.model.ShopRequest
import com.example.loyaltyrewardapp.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdminCURShopViewModel @Inject constructor() : ViewModel(){
    private val _screenState = mutableStateOf<String>("")
    val screenState get() = _screenState

    private val _shop = mutableStateOf<DetailShop?>(null)
    val shop get() = _shop

    private val _isEdited = mutableStateOf<Boolean>(false)
    val isEdited get() = _isEdited

    suspend fun getDetailShop(typeAction: String, id: String){
        _screenState.value = typeAction
        //Lấy dữ liệu từ api
        if (typeAction != "C") {
            val shop = ApiSingleton.getApiService().getShopById(id)
            _shop.value = shop
        } else {
            val shop = DetailShop("", "", "", "", "", "", "", Location("", doubleArrayOf(0.0, 0.0).toList()))
            _shop.value = shop
        }
    }

    fun updateDetailShop(context: Context, edited: Boolean){
        val logoUrl = mutableStateOf<String>(_shop.value?.logo.toString())
        Log.d("Admin CUR Coupon", "update detail shop")
        if ("https://res.cloudinary.com" !in _shop.value?.logo.toString()){
            val inputStream = context.contentResolver.openInputStream(Uri.parse(_shop.value?.logo.toString()))
            val file = File(context.cacheDir, "fileProfile.png")
            file.createNewFile()
            file.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            val userhome = UserHomeViewModel()
            viewModelScope.launch {
                val url = userhome.uploadImage2(file)
                Log.d("AdminCURCoupon", url)
                logoUrl.value = url
                subUpdateShop(context, logoUrl = url, edited)
            }
        }else{
            subUpdateShop(context, logoUrl = _shop.value?.logo.toString(), edited)
        }


    }

    private fun subUpdateShop(context: Context, logoUrl: String, edited: Boolean){
        val shopRequest = ShopRequest(
            name = _shop.value?.name.toString(),
            description = _shop.value?.description.toString(),
            address = _shop.value?.address.toString(),
            logo = logoUrl,
            phone = _shop.value?.phone.toString(),
            cover = "",
            point_trigger = "",
            longitude = _shop.value?.location?.coordinates?.get(0).toString(),
            latitude = _shop.value?.location?.coordinates?.get(1).toString()
        )

        if(checkValidShop(shopRequest)){
            if(edited){
                viewModelScope.launch {
                    try {
                        val response = ApiSingleton.getApiService().updateShop(shopRequest)
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    } catch (e: HttpException){
                        if (e.code() in 400..499){
                            Toast.makeText(context, e.response()?.errorBody()?.string() ?: "Lỗi hệ thống", Toast.LENGTH_SHORT).show()
                        }
                        Log.d("exchangeCoupon", "errorExchange: ${e.response()?.errorBody()?.string()}")
                    }
                }
            }else{
                Toast.makeText(context, "Chưa có thay đổi để cập nhật!", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
        }
    }

    fun createDetailShop(context: Context, navController : NavController){
        val logoUrl = mutableStateOf<String>(_shop.value?.logo.toString())
        Log.d("Admin CUR Coupon", "update detail shop")
        if (_shop.value?.logo.toString().isNotBlank()){
            val inputStream = context.contentResolver.openInputStream(Uri.parse(_shop.value?.logo.toString()))
            val file = File(context.cacheDir, "fileProfile.png")
            file.createNewFile()
            file.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            val userhome = UserHomeViewModel()
            viewModelScope.launch {
                val url = userhome.uploadImage2(file)
                Log.d("AdminCURCoupon", url)
                logoUrl.value = url
                subCreateShop(context, url, navController)
            }
        }else{
            subCreateShop(context, _shop.value?.logo.toString(), navController)
        }
    }

    private fun subCreateShop(context: Context, url: String, navController: NavController){
        val shopRequest = ShopRequest(
            name = _shop.value?.name.toString(),
            description = _shop.value?.description.toString(),
            address = _shop.value?.address.toString(),
            logo = url,
            phone = _shop.value?.phone.toString(),
            cover = "",
            point_trigger = "",
            longitude = _shop.value?.location?.coordinates?.get(0).toString(),
            latitude = _shop.value?.location?.coordinates?.get(1).toString()
        )

        if(checkValidShop(shopRequest)){
            viewModelScope.launch {
                try {
                    val response = ApiSingleton.getApiService().createShop(shopRequest)
                    navController.navigate(Screens.ManagerNavigationScreen.name)
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                } catch (e: HttpException){
                    if (e.code() in 400..499){
                        Toast.makeText(context, e.response()?.errorBody()?.string() ?: "Lỗi hệ thống", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("exchangeCoupon", "errorExchange: ${e.response()?.errorBody()?.string()}")
                }
            }
        }else{
            Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValidShop(shop: ShopRequest): Boolean{
        return with(shop) {
            // Kiểm tra các trường dữ liệu
            return@with name.isNotBlank() &&
                    description.isNotBlank() &&
                    address.isNotBlank() &&
                    logo.isNotBlank()
        }
    }

    fun updateShopName(name: String) {
        _shop.value = _shop.value?.copy(name = name)
    }

    fun updateShopDescription(description: String) {
        _shop.value = _shop.value?.copy(description = description)
    }

    fun updateShopAddress(address: String) {
        _shop.value = _shop.value?.copy(address = address)
    }

    fun updateShopPhone(phone: String) {
        _shop.value = _shop.value?.copy(phone = phone)
    }

    fun updateShopLogo(logo: String) {
        Log.d("Admin CURShop", "updated image uri: $logo")
        _shop.value = _shop.value?.copy(logo = logo)
    }

    fun updateShopCover(cover: String?) {
        _shop.value = _shop.value?.copy(cover = cover)
    }

    fun updateShopLocation(location: Location?) {
        _shop.value = _shop.value?.copy(location = location)
    }

    fun updateShopYourPoints(yourPoints: Int?) {
        _shop.value = _shop.value?.copy(your_points = yourPoints)
    }

    fun addCoupon(coupon: Coupon) {
        val updatedCoupons = _shop.value?.coupons?.toMutableList()
        updatedCoupons?.add(coupon)
        _shop.value = _shop.value?.copy(coupons = updatedCoupons ?: emptyList())
    }

    fun removeCoupon(coupon: Coupon) {
        val updatedCoupons = _shop.value?.coupons?.toMutableList()
        updatedCoupons?.remove(coupon)
        _shop.value = _shop.value?.copy(coupons = updatedCoupons ?: emptyList())
    }
    fun updateScreenState(state: String) {
        _screenState.value = state
    }

    fun updateIsEdited(edited: Boolean){
        _isEdited.value = edited
    }
}