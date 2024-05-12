package com.example.loyaltyrewardapp.data.model

data class DetailShop(
    val _id: String,
    val name: String,
    val description: String,
    val address: String,
    val phone: String,
    val logo: String,
    val cover: String?,
    val location: Location?,
    val your_points: Int? = 0,
    val coupons: List<Coupon> = emptyList(),
    val services: List<DetailShopService> = emptyList(),
)

data class ShopDaily(
    val luotTichDiem: Int? = 0,
    val luotDoiQua: Int? = 0,
    val luotDoiQuaTaiCuaHang: Int? = 0,
    val diemDaCap: Int? = 0,
    val visited: List<Visited> = emptyList()
)


