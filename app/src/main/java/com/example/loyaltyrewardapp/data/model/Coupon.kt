package com.example.loyaltyrewardapp.data.model

import java.io.Serializable

data class Coupon (
    val _id: String,
    val couponName: String,
    val description: String,
    val point: Int,
    val timeExpire: Int,
    val imageUri: String,
    val isActive: Boolean
)