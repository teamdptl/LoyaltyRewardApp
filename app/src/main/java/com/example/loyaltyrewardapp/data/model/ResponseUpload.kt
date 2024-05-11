package com.example.loyaltyrewardapp.data.model

data class ResponseUpload(
    val upload_path: String = "",
    val message: String = "",
    val errors: Map<String, List<String>> = emptyMap()
)
