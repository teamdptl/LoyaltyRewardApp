package com.example.loyaltyrewardapp.data.api

import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.CouponResponse
import com.example.loyaltyrewardapp.data.model.Shop
import com.example.loyaltyrewardapp.data.model.Transaction
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserPoint
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // 1. Lấy thông tin người dùng hiện tại
    @GET("user")
    suspend fun getCurrentUser(): User

    // 2. Lấy các cửa hàng gợi ý cho người dùng (có thể dựa theo vị trí hoặc cửa hàng mới)
    @GET("user/recommended")
    suspend fun getRecommendedShops(@Query("lat") lat: Double, @Query("long") long: Double, @Query("limit") limit: Int): List<Shop>

    // 3. Lấy các cửa hàng đã ghé thăm của người dùng (limit dùng để hạn chế số lượng)
    @GET("user/visited")
    suspend fun getVisitedShops(@Query("limit") limit: Int = 10): List<Shop>

    // 4. Lấy danh sách điểm các cửa hàng của người dùng
    @GET("user/points")
    suspend fun getUserPoints(): List<UserPoint>

    // 5.  Lấy danh sách coupon có thể đổi được của người dùng với các cửa hàng
//    @GET("user/coupons/available")
//    suspend fun getAvailableCoupons(): List<Coupon>

    // 6. Lấy lịch sử điểm đã ghé thăm của người dùng
    @GET("user/transaction")
    suspend fun getUserTransactions(): List<Transaction>

    @GET("shop/{id}")
    suspend fun getShopById(@Path("id") id: String): Shop


    @GET("user/coupons")
    suspend fun getCounpons(): List<CouponResponse>


}