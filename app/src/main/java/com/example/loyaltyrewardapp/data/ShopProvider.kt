package com.example.loyaltyrewardapp.data
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.ShopModel

object ShopProvider {

    val shop =
        ShopModel(
            id = 1,
            name = "Viettel",
            pictureUrl = R.drawable.avatar,
            address = "120 Trần Duy Tôn",
        )
    val shopList =  listOf(
        shop,
        ShopModel(
            id = 2,
            name = "Samsung",
            pictureUrl = R.drawable.avatar,
            address = "12 Nguyễn Cư Trinh",
        ),
        ShopModel(
            id = 3,
            name = "HTC",
            pictureUrl = R.drawable.avatar,
            address = "Trần Hưng Đạo",
        ),
        ShopModel(
            id = 4,
            name = "Google",
            pictureUrl = R.drawable.avatar,
            address = "Lý Tự Trọng",
        )

        )

}