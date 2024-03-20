package com.example.loyaltyrewardapp.data.model

import java.io.Serializable

data class HistoryModel(
    val id: Int,
    val title: String,
    val pictureUrl: Int = 0,
    val time: String,
    val des: String,
    val point: Long
) : Serializable
