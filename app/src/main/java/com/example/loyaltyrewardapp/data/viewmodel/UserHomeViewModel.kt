package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class UserHomeViewModel: ViewModel() {
    val text : MutableState<String> = mutableStateOf("c")
    val user: MutableState<User> = mutableStateOf(UserEmptyState)

    fun fetchCurrentUser() {
        viewModelScope.launch {
            val currentUser = ApiSingleton.getApiService().getCurrentUser()
            user.value = currentUser
        }
    }
}