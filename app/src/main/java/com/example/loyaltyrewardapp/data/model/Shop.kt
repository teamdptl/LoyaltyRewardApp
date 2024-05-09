package com.example.loyaltyrewardapp.data.model

data class Shop(
    val _id: String,
    val name: String,
    val description: String,
    val address: String,
    val phone: String,
    val logo: String,
    val cover: String?,
    val location: Location?,
)

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