package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.HistoryModel
import com.example.loyaltyrewardapp.data.model.Transaction
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {
    var histories : MutableState<List<Transaction>> = mutableStateOf(emptyList<Transaction>())

    fun getHistoryPoint(){
        viewModelScope.launch {
            try {
                val history_point  = ApiSingleton.getApiService().getUserTransactions()
                histories.value = history_point
            } catch (e: Exception) {
                // Handle error
                Log.e("Error", "Failed to fetch history: ${e.message}")
            }
        }
    }
}