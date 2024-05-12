package com.example.loyaltyrewardapp.data.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import com.example.loyaltyrewardapp.navigation.Screens
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SenOTPViewModel: ViewModel() {
    val sendotpphone : MutableState<ResponseMessage?> = mutableStateOf(null)
    val errorMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)
    fun sendCodeOTP(phone: String, context: Context, navController: NavHostController){
        viewModelScope.launch {
            try {
                val codeOTP  = ApiSingleton.getApiService().sendOTP(phone)
                sendotpphone.value = codeOTP
                navController.navigate(Screens.OTPScreens.name)
            } catch (e: HttpException){
                if (e.code() in 400..499){
                    errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string() ?: "Số điện thoại đã được sử dụng")
                    Toast.makeText(context, "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show()
                    Log.d("CheckPhone?", "CheckPhone?: ${errorMessage.value}")
                    isCreateAccount.isCheckPhone = false
                }
            }
        }
    }
}
object isCreateAccount{
    var isCheckPhone: Boolean = false
    var isCheckOTP: Boolean = false

}
class  RegisterViewModel: ViewModel(){
    val dataregister : MutableState<ResponseMessage?> = mutableStateOf(null)
    val errorMessage : MutableLiveData<ResponseMessage?> = MutableLiveData(null)

     fun  createResgister(
         name: String,
         phone: String,
         password: String,
         role: String,
         otp: String,
         context: Context,
         navController: NavController
     ){
        viewModelScope.launch {
            try {
                val cregister = ApiSingleton.getApiService().createAccount(name,phone,password, role, otp)

                dataregister.value = cregister
                navController.navigate(Screens.doneOTPScreen.name)
                isCreateAccount.isCheckOTP = true
            } catch (e: HttpException) {
                // Handle error
                if (e.code() in 400 .. 499){
                    errorMessage.value = ResponseMessage(message = e.response()?.errorBody()?.string()?:"Số điện thoại đã được sử dụng")
                    Toast.makeText(context,"Mã OTP không đúng",Toast.LENGTH_SHORT).show()
                    Log.d("exchangeCheckOTP?", "errorExchangeOTP?: ${errorMessage.value}")
                    isCreateAccount.isCheckOTP = false
                }
                Log.e("Error", "Failed to fetch history: ${e.message}")
            }
        }
    }
}