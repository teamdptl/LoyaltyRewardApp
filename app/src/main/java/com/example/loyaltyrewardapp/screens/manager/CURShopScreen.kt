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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyaltyrewardapp.components.ImagePicker
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURShopViewModel

class DetailShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                CURShopScreen()
            }
        }
    }
}

@Composable
fun CURShopScreen(viewModel: AdminCURShopViewModel = viewModel()){
    val shop by viewModel.shop
    val screenState by viewModel.screenState
    var title by remember{mutableStateOf("")}

    title = if (screenState == "R") {
        "Thông tin cửa hàng"
    }else{
        if (screenState == "C") {
            "Thêm cửa hàng"
        }else{
            "Cập nhật cửa hàng"
        }
    }

    MainBackgroundScreen(title = title) {
        Column(
            Modifier
                .padding(40.dp, 30.dp)
                .background(Color.White)
        ) {
            shop?.let { shop ->
                LabelTextField(
                    label = "Tên cửa hàng",
                    fieldValue = shop.name,
                    numOfRow = 1,
                    {},
                    screenState = screenState
                )
                Spacer(modifier = Modifier.size(10.dp))
                LabelTextField(
                    label = "Mô tả",
                    fieldValue = shop.description,
                    numOfRow = 4,
                    {},
                    screenState = screenState
                )
                Spacer(modifier = Modifier.size(10.dp))
                LabelTextField(
                    label = "Địa chỉ",
                    fieldValue = shop.address,
                    numOfRow = 1,
                    onValueChange = {},
                    screenState = screenState
                )
                Spacer(modifier = Modifier.size(10.dp))
                LabelTextField(
                    label = "Số điện thoại",
                    fieldValue = shop.phone,
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

                    ImagePicker(text = "Choose Image", shop.logo)
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

                    shop.cover?.let { ImagePicker(text = "Choose Image", it) }
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
                    Text(text = "Kinh độ ${shop.location?.coordinates?.get(0)}, vĩ độ ${shop.location?.coordinates?.get(1)}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        color = Color(0xFFCCEBF5),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp)
                }


                Spacer(modifier = Modifier.size(30.dp))
            }

        }
    }
}

@Preview
@Composable
private fun Preview(){
    CURShopScreen()
}