package com.example.loyaltyrewardapp.data.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.User
import kotlinx.coroutines.launch

class UserHomeViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    val error = mutableStateOf<String>("")
    val loading = mutableStateOf<Boolean>(true)

    fun fetchCurrentUser() {
        viewModelScope.launch {
            try {
                loading.value = true
                val currentUser = ApiSingleton.getApiService().getCurrentUser()
                _user.value = currentUser
            } catch (e: Exception) {
                // Handle error
                error.value = e.message.toString()
            } finally {
                loading.value = false
            }
        }
    }

}