package com.example.loyaltyrewardapp.data.model

data class CouponResponse (
    val _id: String,
    val name: String,
    val description: String,
    val require_point: Int,
    val icon: String,
    val id_active: Boolean,
    val expired_after: Int,
    val shop_id: String,
    val expired_at: String,
    val create_at: String,
)

