package com.example.loyaltyrewardapp.data.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
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
import com.example.loyaltyrewardapp.data.model.ResponseUpload
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
import java.io.InputStream

class AdminCURCouponViewModel : ViewModel() {
    private val _screenState = mutableStateOf<String>("")
    val screenState get() = _screenState

    private val _coupon = mutableStateOf<Coupon?>(null)
    val coupon get() = _coupon

    private val _isEdited = mutableStateOf<Boolean>(false)
    val isEdited get() = _isEdited

    val successMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)
    val errorMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)

    suspend fun getDetailCoupon(typeAction: String, id: String) {
        _screenState.value = typeAction
        //Lấy dữ liệu từ api nếu state không phải create
        try {
            if (typeAction != "C") {
                Log.d("Edit coupon", "getDetailCoupon: $id")
                val coupon = ApiSingleton.getApiService().getCouponById(id)
                _coupon.value = coupon
            } else {
                val coupon = Coupon("", "", "", 0, "", false, 0, null)
                _coupon.value = coupon
            }
        } catch (e: HttpException) {
            if (e.code() in 400..499) {
                errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
            }
            Log.d("getDetailCoupon", "error: ${e.response()?.errorBody()?.string()}")
        }


    }

    fun updateDetailCoupon(context: Context){
        val iconUrl = mutableStateOf<String>(_coupon.value?.icon.toString())

        if ("https://res.cloudinary.com" !in _coupon.value?.icon.toString()){
            val inputStream = context.contentResolver.openInputStream(Uri.parse(_coupon.value?.icon))
            val file = File(context.cacheDir, "fileProfile.png")
            file.createNewFile()
            file.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            val userhome = UserHomeViewModel()
            viewModelScope.launch {
                val url = userhome.uploadImage2(file)
                Log.d("AdminCURCoupon", url)
                iconUrl.value = url
                subUpdateCoupon(context, url)
            }
        }else{
            subUpdateCoupon(context, _coupon.value?.icon.toString())
        }

    }

    private fun subUpdateCoupon(context:Context, iconUrl:String){
        val couponRequest = CouponRequest(
            name = _coupon.value?.name.toString(),
            description = _coupon.value?.description.toString(),
            require_point = _coupon.value?.require_point,
            icon = iconUrl,
            is_active = _coupon.value?.is_active,
            expired_after = _coupon.value?.expired_after
        )



        if(checkValidCoupon(couponRequest)){
            if(_isEdited.value){
                viewModelScope.launch {
                    try {
                        val response = ApiSingleton.getApiService().updateCoupon(_coupon.value?._id.toString(), couponRequest)
                        successMessage.value = response
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    } catch (e: HttpException){
                        if (e.code() in 400..499){
                            errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
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

    fun createDetailCoupon(context: Context){
        Log.d("AdminCURCouponViewModel", "image url: ${coupon.value?.icon}")
        val iconUrl = mutableStateOf<String>(_coupon.value?.icon.toString())
        if (_coupon.value?.icon.toString().isNotBlank()){
            val inputStream = context.contentResolver.openInputStream(Uri.parse(_coupon.value?.icon))

            val file = File(context.cacheDir, "fileProfile.png")
            file.createNewFile()
            file.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            val userhome = UserHomeViewModel()
            viewModelScope.launch {
                val url = userhome.uploadImage2(file)
                Log.d("AdminCURCoupon", url)
                iconUrl.value = url
                subCreateCoupon(context, url)
            }
        }else{
            subCreateCoupon(context, "")
        }

    }

    fun subCreateCoupon(context: Context, url: String){
        val couponRequest = CouponRequest(
            name = _coupon.value?.name.toString(),
            description = _coupon.value?.description.toString(),
            require_point = _coupon.value?.require_point,
            icon = url,
            is_active = _coupon.value?.is_active,
            expired_after = _coupon.value?.expired_after
        )

        if (checkValidCoupon(couponRequest)){
            viewModelScope.launch {
                try {
                    val response = ApiSingleton.getApiService().createCoupon(couponRequest)
                    successMessage.value = response
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                } catch (e: HttpException){
                    if (e.code() in 400..499){
                        errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
                        Toast.makeText(context, e.response()?.errorBody()?.string() ?: "Lỗi hệ thống", Toast.LENGTH_SHORT).show()
                    }
                    Log.d("exchangeCoupon", "errorExchange: ${e.response()?.errorBody()?.string()}")
                }
            }
        }else{
            Toast.makeText(context, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
        }

    }

    fun checkValidCoupon(coupon: CouponRequest): Boolean{
        return with(coupon) {
            // Kiểm tra các trường dữ liệu
            return@with name.isNotBlank() &&
                    description.isNotBlank() &&
                    require_point != null &&
                    require_point >= 0 &&
                    icon.isNotBlank() &&
                    expired_after != null &&
                    expired_after > 0 &&
                    is_active != null
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
        Log.d("Admin CURCoupon", "updated image uri: $uri")
        _coupon.value = _coupon.value?.copy(icon = uri)
    }

    fun updateIsActive(isActive: Boolean) {
        _coupon.value = _coupon.value?.copy(is_active = isActive)
    }

    fun updateScreenState(state: String) {
        _screenState.value = state
    }

    fun updateIsEdited(edited: Boolean){
        _isEdited.value = edited
    }

}