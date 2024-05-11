package com.example.loyaltyrewardapp.data.model

import java.io.File

data class CouponRequest (
    val _id: String = "",
    val name: String = "",
    val description: String = "",
    val require_point: Int? = 0,
    val icon: File? = File("/res/drawable/coin_icon.png"),
    val is_active: Boolean? = true,
    val expired_after: Int? = 0
)