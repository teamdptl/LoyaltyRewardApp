package com.example.loyaltyrewardapp.data.model

data class User (
    val _id: String = "",
    val role: String = "",
    val auth_id: String = "",
    val qr: String? = null,
    val shop: DetailShop? = null,
    val created_at: String = "",
)

val UserEmptyState = User(role="empty")
val NotFoundUserState = User(role="not_found")

data class UserInfo(
    val name: String,
    val phone: String,
    val photo: String,
)