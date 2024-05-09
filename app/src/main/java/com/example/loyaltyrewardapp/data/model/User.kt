package com.example.loyaltyrewardapp.data.model

data class User (
    val _id: String = "",
    val role: String = "",
    val auth_id: String = "",
    val qr: String? = null,
    val shop: Shop? = null,
    val created_at: String = "",
)

val UserEmptyState = User()