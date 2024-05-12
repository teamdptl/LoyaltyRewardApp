package com.example.loyaltyrewardapp.data.model

data class ShopRequest(
    val name: String,
    val address: String,
    val logo: String,
    val description: String,
    val phone: String,
    val cover: String,
    val point_trigger: String,
    val latitude: String,
    val longitude: String
)