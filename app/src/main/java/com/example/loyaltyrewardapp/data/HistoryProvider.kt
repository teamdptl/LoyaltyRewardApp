package com.example.loyaltyrewardapp.data

import android.annotation.SuppressLint
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.HistoryModel
import java.text.SimpleDateFormat
import java.util.Locale

object HistoryProvider {
    @SuppressLint("ConstantLocale")
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currentTimeMillis = System.currentTimeMillis()

    val history =
        HistoryModel(
            id = 1,
            title = "Cua hang tien loi cua Phuc va Duy",
            pictureUrl = R.drawable.avatar,
            time = dateFormat.format(currentTimeMillis),
            des = "sao cung duoc, kì cục dữ vậy, bực quá à, tức quá trời tức rồi trời ơi là trời ",
            point = 10
        )

//    val historyList = listOf(
//        history,
//        HistoryModel(
//            id = 2,
//            title = "Tiệm sửa xe",
//            pictureUrl = R.drawable.background,
//            time = dateFormat.format(currentTimeMillis),
//            des = "khong duoc",
//            point = 50
//        ),
//        HistoryModel(
//            id = 3,
//            title = "Tiệm giặt ủi",
//            pictureUrl = R.drawable.user,
//            time = dateFormat.format(currentTimeMillis),
//            des = "tuyet voi",
//            point = 20
//        ),
//        HistoryModel(
//            id = 4,
//            title = "Tiệm rửa",
//            pictureUrl = R.drawable.user,
//            time = dateFormat.format(currentTimeMillis),
//            des = "mệt ghê",
//            point = 20
//        ),
//        HistoryModel(
//            id = 5,
//            title = "Tiệm rửa",
//            pictureUrl = R.drawable.user,
//            time = dateFormat.format(currentTimeMillis),
//            des = "mệt ghê",
//            point = 20
//        ),
//        HistoryModel(
//            id = 6,
//            title = "Tiệm rửa",
//            pictureUrl = R.drawable.user,
//            time = dateFormat.format(currentTimeMillis),
//            des = "mệt ghê",
//            point = 20
//        ),
//        HistoryModel(
//            id = 7,
//            title = "Tiệm rửa chén",
//            pictureUrl = R.drawable.user,
//            time = dateFormat.format(currentTimeMillis),
//            des = "mệt ghê",
//            point = 20
//        ),
//    )


}