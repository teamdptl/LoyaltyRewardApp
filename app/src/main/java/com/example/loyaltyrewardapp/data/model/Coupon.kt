package com.example.loyaltyrewardapp.data.model

data class DetailShopCoupon(
    val _id: String = "",
    val name: String = "",
    val description: String = "",
    val require_point: Int = 0,
    val icon: String = "",
    val is_active: Boolean = true,
    val expired_after: Int = 0,
    val shop: Shop? = null,
)