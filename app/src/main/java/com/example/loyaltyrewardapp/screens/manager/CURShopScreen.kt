package com.example.loyaltyrewardapp.screens.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.components.ImagePicker
import com.example.loyaltyrewardapp.components.MainBackgroundScreen

class DetailShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                CURShopScreen(
                    shopName = "",
                    description = "",
                    address = "",
                    phone = "",
                    logoUri = "",
                    bannerUri = "",
                    longDeg = 1.4516f,
                    latDeg = 1.546f,
                    screenState = ""
                )
            }
        }
    }
}

@Composable
fun CURShopScreen(
    shopName: String,
    description: String,
    address: String,
    phone: String,
    logoUri: String,
    bannerUri: String,
    longDeg: Float,
    latDeg: Float,
    screenState: String
){
    MainBackgroundScreen(title = "Tạo cửa hàng") {
        Column(
            Modifier
                .padding(40.dp, 30.dp)
                .background(Color.White)
        ) {
            LabelTextField(
                label = "Tên cửa hàng",
                fieldValue = shopName,
                numOfRow = 1,
                {},
                screenState = screenState
            )
            Spacer(modifier = Modifier.size(10.dp))
            LabelTextField(
                label = "Mô tả",
                fieldValue = description,
                numOfRow = 4,
                {},
                screenState = screenState
            )
            Spacer(modifier = Modifier.size(10.dp))
            LabelTextField(
                label = "Điểm sử dụng",
                fieldValue = address,
                numOfRow = 1,
                onValueChange = {},
                screenState = screenState
            )
            Spacer(modifier = Modifier.size(10.dp))
            LabelTextField(
                label = "Hết hạn sau (tháng)",
                fieldValue = phone,
                numOfRow = 1,
                onValueChange = {},
                screenState = screenState
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = "Logo",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                ImagePicker(text = "Choose Image", logoUri)
            }

            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = "Ảnh bìa",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                ImagePicker(text = "Choose Image", bannerUri)
            }

            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = "Định vị",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Kinh độ $longDeg, vĩ độ $latDeg",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color(0xFFCCEBF5),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp)
            }


            Spacer(modifier = Modifier.size(30.dp))
            GroupButtonAction(screenState = "R",
                "Xác nhận",
                "Lưu thay đổi",
                "Chỉnh sửa")
        }
    }
}

@Preview
@Composable
private fun Preview(){
    CURShopScreen(
        shopName = "",
        description = "",
        address = "",
        phone = "",
        logoUri = "",
        bannerUri = "",
        longDeg = 1.4516f,
        latDeg = 1.546f,
        screenState = ""
    )
}