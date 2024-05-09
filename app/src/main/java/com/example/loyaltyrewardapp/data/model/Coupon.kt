package com.example.loyaltyrewardapp.data.model

data class Coupon (
    val _id: String,
    val name: String,
    val description: String,
    val require_point: Int,
    val expired_after: Int,
    val icon: String,
    val is_active: Boolean,
    val shop: Shop
)