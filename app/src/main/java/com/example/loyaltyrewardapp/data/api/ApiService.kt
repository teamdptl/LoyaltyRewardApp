package com.example.loyaltyrewardapp.data.api


import com.example.loyaltyrewardapp.data.model.Coupon
import com.example.loyaltyrewardapp.data.model.CouponRequest
import com.example.loyaltyrewardapp.data.model.UserCouponResponse
import com.example.loyaltyrewardapp.data.model.DetailShop
import com.example.loyaltyrewardapp.data.model.ResponseMessage
import com.example.loyaltyrewardapp.data.model.ResponseUpload
import com.example.loyaltyrewardapp.data.model.ShopRequest
import com.example.loyaltyrewardapp.data.model.Transaction
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserInfo
import com.example.loyaltyrewardapp.data.model.UserPoint
import com.example.loyaltyrewardapp.data.model.ServiceResponse
import retrofit2.http.Body
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.ResourceBundle

interface ApiService {
    // 1. Lấy thông tin người dùng hiện tại
    @GET("user")
    suspend fun getCurrentUser(): User

    // 2. Lấy các cửa hàng gợi ý cho người dùng (có thể dựa theo vị trí hoặc cửa hàng mới)
    @GET("user/recommended")
    suspend fun getRecommendedShops(@Query("limit") limit: Int, @Query("lat") lat: Double? = null, @Query("long") long: Double? = null): List<DetailShop>

    // 3. Lấy các cửa hàng đã ghé thăm của người dùng (limit dùng để hạn chế số lượng)
    @GET("user/visited")
    suspend fun getVisitedShops(@Query("limit") limit: Int = 10): List<DetailShop>

    // 4. Lấy danh sách điểm các cửa hàng của người dùng
    @GET("user/points")
    suspend fun getUserPoints(): List<UserPoint>

    // 5.  Lấy danh sách coupon có thể đổi được của người dùng với các cửa hàng
    @GET("user/coupons/available")
    suspend fun getAvailableCoupons(): List<Coupon>

    // 6. Lấy lịch sử điểm đã ghé thăm của người dùng
    @GET("user/transaction")
    suspend fun getUserTransactions(): List<Transaction>

//    8. Lấy thông tin cửa hàng theo id
    @GET("shop/{id}")
    suspend fun getShopById(@Path("id") id: String): DetailShop

    // 11. Lấy các ưu đãi có sẵn của bản thân
    @GET("user/coupons")
    suspend fun getCoupons(): List<UserCouponResponse>


//    9. Lấy thông tin chi tiết của ưu đãi
    @GET("coupon/{id}")
    suspend fun getCouponById(@Path("id") id: String): Coupon

    // 10. Đổi điểm thành ưu đãi cửa hàng
    @POST("user/exchange")
    suspend fun exchangeCoupon(@Query("coupon_id") couponId: String): ResponseMessage
    
//    @GET("coupon/{id}")
//    suspend fun getCouponById(@Path("id") id: String): DetailShopCoupon

    @GET("user/coupon/{id}")
    suspend fun getCouponUser(@Path("id") id: String): UserCouponResponse


//    17. Quét QR người dùng tích điểm hoặc nhận ưu đãi
    @POST("shop/scan")
    suspend fun scanQR(@Query("user_id") user_id: String, @Query("reward_id") reward_id: String?, @Query("service_id") service_id: String?): ResponseMessage

    @POST("upload")
    @Multipart
    suspend fun uploadImg(@Part file: MultipartBody.Part): ResponseUpload

    @POST("shop/coupon/{id}")
    suspend fun updateCoupon(@Path("id") id: String, @Body coupon: CouponRequest): ResponseMessage

    @POST("shop/coupon")
    suspend fun createCoupon(@Body coupon: CouponRequest): ResponseMessage

    @POST("shop/update")
    suspend fun updateShop(@Body shop: ShopRequest): ResponseMessage

    @POST("shop")
    suspend fun createShop(@Body shop: ShopRequest): ResponseMessage

    @GET("service/{id}")
    suspend fun getServiceById(@Path("id") id: String): ServiceResponse

    @POST("shop/service/{id}")
    suspend fun updateService(@Path("id") id: String, @Body service: ServiceResponse): ResponseMessage

    @POST("")
    suspend fun createService()
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: String): UserInfo
}
