package com.example.loyaltyrewardapp.data

import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.ShopModel

object ShopProvider {

    val shop =
        ShopModel(
            id = 1,
            title = "Tiệm rửa xe của Phúc",
            pictureUrl = R.drawable.promotion,
            address = "120 Trần Duy Tôn",
            status = true,
        )

    val shopList = listOf(
        shop,
        ShopModel(
            id = 2,
            title = "Tiệm giặt ủi bà Hai",
            pictureUrl = R.drawable.promotion,
            address = "120 Trần Hưng Đạo",
            status = true,
        ),
        ShopModel(
            id = 3,
            title = "Tiệm sửa xe ông Tuấn",
            pictureUrl = R.drawable.promotion,
            address = "12 Nguyễn Khắc Duy",
            status = true,
        ),
        ShopModel(
            id = 4,
            title = "Tiệm tạp hóa",
            pictureUrl = R.drawable.promotion,
            address = "Con đường hạnh phúc",
            status = false,
        ),
        ShopModel(
            id = 5,
            title = "Tiệm rửa chén",
            pictureUrl = R.drawable.promotion,
            address = "Con đường thành công",
            status = false,
        ),
    )
}