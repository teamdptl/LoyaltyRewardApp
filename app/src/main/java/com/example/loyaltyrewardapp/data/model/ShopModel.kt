package com.example.loyaltyrewardapp.data.model

import java.io.Serializable

data class ShopModel(
    val id: Int,
    val name: String,
    val pictureUrl: Int = 0,
    val address: String,
): Serializable
