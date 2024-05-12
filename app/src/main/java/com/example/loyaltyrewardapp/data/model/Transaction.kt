package com.example.loyaltyrewardapp.data.model

data class Transaction(
    val _id: String = "",
    val point: Int = 0, // điểm dc cộng
    val current_point: Int = 0, // điểm hiện tại
    val reason: String="",
    val type: String="", // plus, minus, receive
    val created_at: String="",
    val shop: DetailShop,
)
