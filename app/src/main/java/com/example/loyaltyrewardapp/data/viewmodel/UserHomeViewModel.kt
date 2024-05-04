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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserHomeViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()

    val isLoading: MutableState<Boolean> = mutableStateOf(true)

    fun fetchCurrentUser() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val currentUser = ApiSingleton.getApiService().getCurrentUser()
                _user.value = currentUser
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading.value = false
            }
        }
    }

}