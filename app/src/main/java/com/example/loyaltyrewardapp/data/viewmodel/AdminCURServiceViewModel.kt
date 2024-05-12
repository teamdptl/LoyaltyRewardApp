package com.example.loyaltyrewardapp.data.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.ServiceResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AdminCURServiceViewModel: ViewModel() {
    private val _screenState = mutableStateOf<String>("")
    val screenState get() = _screenState

    private val _service = mutableStateOf<ServiceResponse?>(null)
    val service get() = _service

    private val _isEdited = mutableStateOf<Boolean>(false)
    val isEdited get() = _isEdited

    suspend fun getDetailService(typeAction: String, id: String){
        _screenState.value = typeAction
        //Lấy dữ liệu từ api
        if (typeAction != "C") {
            val service = ApiSingleton.getApiService().getServiceById(id)
            _service.value = service
        } else {
            val service = ServiceResponse()
            _service.value = service
        }
    }

    fun updateDetailService(context: Context, edited: Boolean){
        val serviceRequest = ServiceResponse(
            name = _service.value?.name.toString(),
            description = _service.value?.description.toString(),
            should_notification = _service.value?.should_notification?:false,
            period = _service.value?.period?:0,
            points_reward = _service.value?.points_reward?:0,
        )

        if(checkValidShop(serviceRequest)){
            if(edited){
                viewModelScope.launch {
                    try {
                        val response = ApiSingleton.getApiService().updateService(_service.value?._id.toString(),serviceRequest)
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

    fun createDetailService(context: Context){
        val serviceRequest = ServiceResponse(
            name = _service.value?.name.toString(),
            description = _service.value?.description.toString(),
            should_notification = _service.value?.should_notification?:false,
            period = _service.value?.period?:0,
            points_reward = _service.value?.points_reward?:0,
        )

        if(checkValidShop(serviceRequest)){
            viewModelScope.launch {
                try {
                    val response = ApiSingleton.getApiService().createService(serviceRequest)
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

    private fun checkValidShop(service: ServiceResponse): Boolean{
        return with(service) {
            // Kiểm tra các trường dữ liệu
            return@with name.isNotBlank() &&
                    description.isNotBlank() &&
                    period != null &&
                    period > 0 &&
                    points_reward != null &&
                    points_reward > 0

        }
    }



    fun updateServiceName(name: String){
        _service.value = _service.value?.copy(name = name)
    }

    fun updateServiceDescription(description: String){
        _service.value = _service.value?.copy(description = description)
    }

    fun updateServiceNotify(isNotify: Boolean){
        _service.value = _service.value?.copy(should_notification = isNotify)
    }

    fun updateServicePeriod(period: Int){
        _service.value = _service.value?.copy(period = period)
    }

    fun updateServicePoint(point: Int){
        _service.value = _service.value?.copy(points_reward = point)
    }

    fun updateScreenState(screenState: String){
        _screenState.value = screenState
    }

    fun updateIsEdited(edited: Boolean){
        _isEdited.value = edited
    }
}