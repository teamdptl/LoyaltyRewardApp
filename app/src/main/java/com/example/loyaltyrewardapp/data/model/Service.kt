package com.example.loyaltyrewardapp.data.model

data class DetailShopService(
    val _id: String = "",
    val name: String = "",
    val description: String = "",
    val should_notification: Boolean = false,
    val period: Int = 0,
    val points_reward: Int = 0
)
