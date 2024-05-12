package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class ManagerScanViewModel @Inject constructor() : ViewModel() {
    val successRewardMessage : MutableState<ResponseMessage?> = mutableStateOf(null)
    val errorRewardMessage : MutableState<ResponseMessage?> = mutableStateOf(null)
    val accumulateResponse : MutableLiveData<ResponseMessage?> = MutableLiveData(null)
    val errorAccumulateMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)

    fun receiveReward(userId: String, rewardId: String){
        viewModelScope.launch {
            try {
                val data = ApiSingleton.getApiService().scanQR(userId, rewardId, null)
                successRewardMessage.value = data
                Log.d("exchangeCoupon", "exchangeCoupon: ${successRewardMessage.value}")
            } catch (e: HttpException){
                if (e.code() in 400..499){
                    errorRewardMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
                }
                Log.d("exchangeCoupon", "errorExchange: ${errorRewardMessage.value}")
            }
        }
    }

    fun accumulatePoint(userId: String, serviceId: String){
        viewModelScope.launch {
            try {
                val data = ApiSingleton.getApiService().scanQR(userId, null, serviceId)
                accumulateResponse.value = data
                Log.d("ConfirmScanScreen", "ConfirmScanScreen Success: $accumulateResponse")
            } catch (e: HttpException) {
                errorAccumulateMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
                Log.d("ConfirmScanScreen", "ConfirmScanScreen Error: $accumulateResponse")
            }
        }
    }
}