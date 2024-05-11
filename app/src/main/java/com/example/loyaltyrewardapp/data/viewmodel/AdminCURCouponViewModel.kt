package com.example.loyaltyrewardapp.data.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.CouponRequest
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.File

class AdminCURCouponViewModel : ViewModel() {
    private val _screenState = mutableStateOf<String>("")
    val screenState get() = _screenState

    private val _coupon = mutableStateOf<Coupon?>(null)
    val coupon get() = _coupon

    val successMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)
    val errorMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)

    suspend fun getDetailCoupon(typeAction: String, id: String) {
        _screenState.value = typeAction
        //Lấy dữ liệu từ api nếu state không phải create
        if (typeAction != "C") {
            val coupon = ApiSingleton.getApiService().getCouponById(id)
            _coupon.value = coupon
        } else {
            val coupon = Coupon("", "", "", 0, "", false, 0, null)
            _coupon.value = coupon
        }

    }

    fun updateDetailCoupon(context: Context){

        val couponRequest = CouponRequest(
            _id = _coupon.value?._id.toString(),
            name = _coupon.value?.name.toString(),
            description = _coupon.value?.description.toString(),
            require_point = _coupon.value?.require_point,
            icon = File(_coupon.value?.icon.toString()),
            is_active = _coupon.value?.is_active,
            expired_after = _coupon.value?.expired_after
        )
        viewModelScope.launch {
            try {
                val response = ApiSingleton.getApiService().updateCoupon(_coupon.value?._id.toString(), couponRequest)
                successMessage.value = response
                Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                Log.d("exchangeCoupon", "exchangeCoupon: $response")
            } catch (e: HttpException){
                if (e.code() in 400..499){
                    errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
                    Toast.makeText(context, "Lỗi hệ thống", Toast.LENGTH_SHORT).show()
                    Log.d("exchangeCoupon", "errorExchange: ${errorMessage.value}")
                }
                Log.d("exchangeCoupon", "errorExchange: Out of error number")
            }
        }
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