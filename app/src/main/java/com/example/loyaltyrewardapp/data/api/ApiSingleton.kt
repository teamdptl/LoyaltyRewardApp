package com.example.loyaltyrewardapp.data.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiSingleton {
    private const val BASE_URL = "https://app.dy.id.vn/api/"
    private lateinit var sharedPreferences: SharedPreferences
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
    }

    suspend fun getApiService(): ApiService {
        val httpClient = OkHttpClient.Builder()
        val token = getTokenFromLocalStorageOrFirebase()

        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token").build()
            )
        })

        val client = httpClient.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    private suspend fun getTokenFromLocalStorageOrFirebase(): String {
        val firebaseUser = auth.currentUser
        if (firebaseUser == null){
            Log.d("FirebaseAuthToken", "Do not login")
            return ""
        }
        val storedToken = sharedPreferences.getString("idToken", null)
        val expiredTokenTime = sharedPreferences.getLong("expiredTime", 0)
        val previousId = sharedPreferences.getString("firebaseId", null)
        val currentTime = System.currentTimeMillis()
        if (storedToken != null && expiredTokenTime*1000 > currentTime && previousId == firebaseUser.uid){
            Log.d("FirebaseAuthToken", "Get from local")
            return storedToken
        }

        val tokenData = firebaseUser.getIdToken(false).await()
        val idToken = tokenData?.token
        val expiredTime = tokenData?.expirationTimestamp
        Log.d("Expired","Expired"+ expiredTime.toString())
        Log.d("Expired", "Current $currentTime")


        if (idToken != null) {
            with(sharedPreferences.edit()) {
                putString("idToken", idToken)
                putLong("expiredTime", expiredTime ?: 0)
                putString("firebaseId", firebaseUser.uid)
                apply()
            }
        }

        Log.d("FirebaseAuthToken", "Get from firebase")

        return idToken ?: ""
    }
}