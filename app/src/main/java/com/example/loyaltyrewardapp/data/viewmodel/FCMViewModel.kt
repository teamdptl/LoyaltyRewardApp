package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FCMViewModel @Inject constructor() : ViewModel() {
    fun updateFCMToken(token: String) {
        viewModelScope.launch {
            try {
                ApiSingleton.getApiService().updateFCM(token)
            } catch (e: HttpException) {
                Log.e("FCMViewModel", "Error updating FCM token: ${e.message}")
            }
        }
    }
}