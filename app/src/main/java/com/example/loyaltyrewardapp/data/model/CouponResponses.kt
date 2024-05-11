package com.example.loyaltyrewardapp.data.model

data class UserCouponResponse (
    val _id: String = "",
    val name: String = "",
    val description: String = "",
    val require_point: Int = 0,
    val icon: String = "",
    val id_active: Boolean = true,
    val expired_after: Int = 0,
    val shop_id: String = "",
    val expired_at: String = "",
    val create_at: String = "",
    val user_id: String = ""
)

