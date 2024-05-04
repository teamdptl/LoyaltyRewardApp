package com.example.loyaltyrewardapp.data.model

data class Shop(
    val _id: String,
    val name: String,
    val address: String,
    val logo: String,
    val cover: String?,
    val location: Location?,
)
