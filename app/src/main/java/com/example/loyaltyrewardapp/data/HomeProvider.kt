package com.example.loyaltyrewardapp.data

import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.HomeModel

object HomeProvider {

    val home =
        HomeModel(
            id = 1,
            title = "Tiệm rửa xe của Phúc",
            pictureUrl = R.drawable.avatar,
            address = "120 Trần Duy Tôn",
            status = true,
        )

    val homeList = listOf(
        home,
        HomeModel(
            id = 2,
            title = "Tiệm giặt ủi bà Hai",
            pictureUrl = R.drawable.avatar,
            address = "120 Trần Hưng Đạo",
            status = true,
        ),
        HomeModel(
            id = 3,
            title = "Tiệm sửa xe ông Tuấn",
            pictureUrl = R.drawable.avatar,
            address = "12 Nguyễn Khắc Duy",
            status = true,
        ),
        HomeModel(
            id = 4,
            title = "Tiệm tạp hóa",
            pictureUrl = R.drawable.avatar,
            address = "Con đường hạnh phúc",
            status = false,
        ),
    )
}