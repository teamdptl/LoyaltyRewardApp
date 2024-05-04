package com.example.loyaltyrewardapp.data.api

import com.example.loyaltyrewardapp.data.model.Shop
import com.example.loyaltyrewardapp.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("user")
    suspend fun getCurrentUser(): User

    @GET("user/recommended")
    suspend fun getRecommendedShops(@Query("lat") lat: Double, @Query("long") long: Double, @Query("limit") limit: Int): List<Shop>


}