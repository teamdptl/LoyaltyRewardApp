package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import kotlinx.coroutines.launch

class SenOTPViewModel: ViewModel() {
    val sendotpphone : MutableState<ResponseMessage?> = mutableStateOf(null)
    fun sendCodeOTP(phone: String){
        viewModelScope.launch {
            try {
                val codeOTP  = ApiSingleton.getApiService().sendOTP(phone)
                sendotpphone.value = codeOTP
            } catch (e: Exception) {
                // Handle error
                Log.e("Error", "Failed to fetch history: ${e.message}")
            }
        }
    }
}
class  RegisterViewModel: ViewModel(){
    val dataregister : MutableState<ResponseMessage?> = mutableStateOf(null)

     fun  createResgister(name: String, phone: String, password: String, role: String, otp: String){
        viewModelScope.launch {
            try {
                val cregister = ApiSingleton.getApiService().createAccount(name,phone,password, role, otp)

                dataregister.value = cregister
            } catch (e: Exception) {
                // Handle error
                Log.e("Error", "Failed to fetch history: ${e.message}")
            }
        }
    }
}