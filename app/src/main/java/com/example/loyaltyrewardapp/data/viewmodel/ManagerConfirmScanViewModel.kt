package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ManagerConfirmScanViewModel : ViewModel() {
    val user : MutableState<UserInfo?> = mutableStateOf(null)
    val error : MutableState<ResponseMessage?> = mutableStateOf(null)

    fun fetchUser(userId: String){
        viewModelScope.launch {
            try {
                user.value = ApiSingleton.getApiService().getUserById(userId)
                Log.d("TAG", "fetchUser: ${user.value}")
            } catch (e: HttpException){
                error.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Lỗi hệ thống")
                Log.d("TAG", "fetchUser: ${error.value}")
            }
        }
    }
}
