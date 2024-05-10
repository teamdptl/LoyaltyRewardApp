package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.UserPoint
import kotlinx.coroutines.launch

class ListPointViewModel : ViewModel() {
    var listpoints : MutableState<List<UserPoint>> = mutableStateOf(emptyList<UserPoint>())

    fun getListPoint(){
        viewModelScope.launch {
            try {
                val list_point  = ApiSingleton.getApiService().getUserPoints()
                listpoints.value = list_point
            } catch (e: Exception) {
                // Handle error
                Log.e("Error", "Failed to fetch list point: ${e.message}")
            }
        }
    }
}