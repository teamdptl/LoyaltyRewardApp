package com.example.loyaltyrewardapp.data.model

data class User (
    val _id: String,
    val role: String,
    val auth_id: String,
    val qr: String?,
    val shop: Shop?,
)