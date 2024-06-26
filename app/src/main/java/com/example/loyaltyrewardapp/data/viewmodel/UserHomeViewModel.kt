package com.example.loyaltyrewardapp.data.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.NotFoundUserState
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import com.example.loyaltyrewardapp.data.model.ResponseUpload
import com.example.loyaltyrewardapp.data.model.DetailShop
import com.example.loyaltyrewardapp.data.model.ShopDaily
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import com.example.loyaltyrewardapp.data.model.Visited
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileNotFoundException
import retrofit2.HttpException

class UserHomeViewModel: ViewModel() {
    val user: MutableState<User> = mutableStateOf(UserEmptyState)
    val recommendShops: MutableState<List<DetailShop>> = mutableStateOf(emptyList<DetailShop>())
    val visitedShops: MutableState<List<DetailShop>> = mutableStateOf(emptyList<DetailShop>())
    val availableCoupons: MutableState<List<Coupon>> = mutableStateOf(emptyList<Coupon>())
    val visitedManagers: MutableState<List<Visited>> = mutableStateOf(emptyList<Visited>())
    val shopDaily: MutableState<ShopDaily?> = mutableStateOf(null)
    val visitedHistorys: MutableState<List<Visited>> = mutableStateOf(emptyList<Visited>())


    fun fetchCurrentUser() {
        viewModelScope.launch {
            try {
                val currentUser = ApiSingleton.getApiService().getCurrentUser()
                user.value = currentUser
                Log.d("Loading", "fetchCurrentUser: ${user.value}")
            } catch (e: HttpException) {
                user.value = NotFoundUserState
            }
        }
    }

    fun fetchRecommendShops(limit: Int = 10) {
        viewModelScope.launch {
            try{
                val shops = ApiSingleton.getApiService().getRecommendedShops(limit)
                recommendShops.value = shops
            } catch (e: HttpException){
                Log.d("Loading", "fetchRecommendShops: ${e.response()?.body().toString()}")
            }
        }
    }

    fun fetchVisitedShops() {
        viewModelScope.launch {
            try {
                val shops = ApiSingleton.getApiService().getVisitedShops()
                visitedShops.value = shops
            } catch (e: HttpException){
                Log.d("Loading", "fetchVisitedShops: ${e.response()?.body().toString()}")
            }

        }
    }

    fun fetchAvailableVoucher() {
        viewModelScope.launch {
            try {
                val vouchers = ApiSingleton.getApiService().getAvailableCoupons()
                availableCoupons.value = vouchers
            } catch (e: HttpException){
                Log.d("Loading", "fetchAvailableVoucher: ${e.response()?.body().toString()}")
            }
        }
    }

    fun fetchVisitedManager(){
        viewModelScope.launch {
            val visites = ApiSingleton.getApiService().visitedOfADay()
            visitedManagers.value = visites.visited
            shopDaily.value = visites
        }
    }

    fun fetchVisitedHistory(){
        viewModelScope.launch {
            val histories = ApiSingleton.getApiService().historyManager()
            visitedHistorys.value = histories
        }
    }

    fun uploadImage(file: File): MutableLiveData<ResponseUpload> {
        val uploadResponse = MutableLiveData<ResponseUpload>()
        viewModelScope.launch {
            try {
                if (!file.exists()) {
                    throw Exception("File does not exist")
                }

                // Log file details
                Log.d("File Details", "Name: ${file.name}, Size: ${file.length()}")

                // Prepare request body
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

                // Upload image using Retrofit
                val response = withContext(Dispatchers.IO) {
                    ApiSingleton.getApiService().uploadImg(body)
                }

//                Log.d("Trả về ph upload", response.message)
                Log.d("Trả về ph upload", response.upload_path)
//                Log.d("Trả về ph upload", response.errors.toString())
                uploadResponse.postValue(response)
                // Handle response here if needed
            } catch (e: FileNotFoundException) {
                // Handle file not found exception
                Log.e("Upload Error", "File not found: ${e.message}")
            } catch (e: Exception) {
                // Handle other exceptions
                Log.e("Upload Error", "Error uploading file: ${e.message}")
            }
        }
//        uploadResponse.value?.message?.let { Log.d("Trả về ph upload", it) }
        uploadResponse.value?.upload_path?.let { Log.d("Trả về ph upload", it) }
//        Log.d("Trả về ph upload", uploadResponse.value?.errors.toString())
        return uploadResponse

    }

    suspend fun uploadImage2(file: File): String {
        try {
            if (!file.exists()) {
                throw Exception("File does not exist")
            }

            // Log file details
            Log.d("File Details", "Name: ${file.name}, Size: ${file.length()}")

            // Prepare request body
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            // Upload image using Retrofit
            val response = withContext(Dispatchers.IO) {
                ApiSingleton.getApiService().uploadImg(body)
            }

//                Log.d("Trả về ph upload", response.message)
            Log.d("Trả về ph upload", response.upload_path)
//                Log.d("Trả về ph upload", response.errors.toString())
            return response.upload_path ?: ""
            // Handle response here if needed
        } catch (e: FileNotFoundException) {
            // Handle file not found exception
            Log.e("Upload Error", "File not found: ${e.message}")
        } catch (e: Exception) {
            // Handle other exceptions
            Log.e("Upload Error", "Error uploading file: ${e.message}")
        }
        return ""
    }

}