package com.example.loyaltyrewardapp.data.model
data class CouponRequest (
    val name: String = "",
    val description: String = "",
    val require_point: Int? = 0,
    var icon: String = "",
    val expired_after: Int? = 0,
    val is_active: Boolean? = true,

    )