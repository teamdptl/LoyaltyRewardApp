package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.NotFoundUserState
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import kotlinx.coroutines.launch

class GuestViewModel : ViewModel() {
    val user: MutableLiveData<User> = MutableLiveData<User>()

    fun fetchCurrentUser() {
        viewModelScope.launch {
            user.value = UserEmptyState
            try {
                Log.e("GuestViewModel", "Fetching current user")
                val currentUser = ApiSingleton.getApiService().getCurrentUser()
                user.value = currentUser
                Log.e("GuestViewModel", "Fetching successfully")
            } catch (e: Exception) {
                Log.e("GuestViewModel", "Error fetching current user: ${e.message}")
                user.value = NotFoundUserState
            }
        }
    }
}