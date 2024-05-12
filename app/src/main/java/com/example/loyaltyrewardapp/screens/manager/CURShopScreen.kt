package com.example.loyaltyrewardapp.screens.manager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.birjuvachhani.locus.Locus
import com.example.loyaltyrewardapp.components.ImagePicker
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.model.Location
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURShopViewModel

class DetailShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
            }
        }
    }
}

@Composable
fun CURShopScreen(navController: NavController = rememberNavController(), shopId: String, screen: String = "R", shopViewModel : AdminCURShopViewModel = AdminCURShopViewModel()){
    var shop by remember {shopViewModel.shop}
    var screenState by remember {shopViewModel.screenState}
    var title by remember{mutableStateOf("")}
    val context = LocalContext.current

    Log.d("CUR Coupon Screen", "render screen")
    LaunchedEffect(key1 = null){
        shopId.let{
            shopViewModel.getDetailShop(screen, it)
            Log.d("Loading", "Dang load du lieu")
        }
    }

    if(shop == null){
        Log.d("Loading", "Chua co du lieu")
    }else{
        title = if (screenState == "R") {
            "Thông tin cửa hàng"
        }else{
            if (screenState == "C") {
                "Thêm cửa hàng"
            }else{
                "Cập nhật cửa hàng"
            }
        }
    }


    MainBackgroundScreen(title = title) {
        Column(
            Modifier
                .padding(40.dp, 30.dp)
                .background(Color.White)
        ) {
            shop?.let { sh ->
                LabelTextField(
                    label = "Tên cửa hàng",
                    fieldValue = sh.name,
                    numOfRow = 1,
                    {
                        if (!shopViewModel.isEdited.value){
                            shopViewModel.updateIsEdited(true)
                        }
                        shopViewModel.updateShopName(it)
                        shop = shop?.copy(name = it)
                    },
                    screenState = screenState
                )
                Spacer(modifier = Modifier.size(10.dp))
                LabelTextField(
                    label = "Mô tả",
                    fieldValue = sh.description,
                    numOfRow = 4,
                    {
                        if (!shopViewModel.isEdited.value){
                            shopViewModel.updateIsEdited(true)
                        }
                        shopViewModel.updateShopDescription(it)
                        shop = shop?.copy(description = it)
                    },
                    screenState = screenState
                )
                Spacer(modifier = Modifier.size(10.dp))
                LabelTextField(
                    label = "Địa chỉ",
                    fieldValue = sh.address,
                    numOfRow = 1,
                    onValueChange = {
                        if (!shopViewModel.isEdited.value){
                            shopViewModel.updateIsEdited(true)
                        }
                        shopViewModel.updateShopAddress(it)
                        shop = shop?.copy(address = it)
                    },
                    screenState = screenState
                )
                Spacer(modifier = Modifier.size(10.dp))
                LabelTextField(
                    label = "Số điện thoại",
                    fieldValue = sh.phone,
                    numOfRow = 1,
                    onValueChange = {
                        if (!shopViewModel.isEdited.value){
                            shopViewModel.updateIsEdited(true)
                        }
                        shopViewModel.updateShopPhone(it)
                        shop = shop?.copy(phone = it)
                    },
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

                    ImagePicker(updateUri =
                    {
                        shopViewModel.updateShopLogo(it)
                        if (!shopViewModel.isEdited.value){
                            shopViewModel.updateIsEdited(true)
                        }
                    },
                        text = "Choose Image", sh.logo, screenState)
                }

//                Spacer(modifier = Modifier.size(10.dp))
//                Column {
//                    Text(
//                        text = "Ảnh bìa",
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Start,
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 16.sp
//                    )
//
//                    ImagePicker(updateUri = {shopViewModel.updateShopCover(it)}, text = "Choose Image", sh.cover.toString(), screenState)
//                }

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
                    Text(text = "Kinh độ ${sh.location?.coordinates?.get(0)}, vĩ độ ${sh.location?.coordinates?.get(1)}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = screenState != "R")
                            {
                                Locus.getCurrentLocation(context) { result ->
                                    result.location?.let {
                                        val coordinate = doubleArrayOf(it.longitude, it.latitude).toList()
                                        val location = Location("Point", coordinates = coordinate)
                                        shopViewModel.updateShopLocation(location)
                                        shop = shop?.copy(location = location)
                                        if (!shopViewModel.isEdited.value){
                                            shopViewModel.updateIsEdited(true)
                                        }
                                        Log.d("TestCamera", "Location: ${it.longitude} ${it.latitude}")
                                    }
                                    result.error?.let { /* Received error! */ }
                                }
                            },
                        textAlign = TextAlign.Start,
                        color = Color(0xFFCCEBF5),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp)
                }


                Spacer(modifier = Modifier.size(30.dp))
                Row(modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
                    if (screenState == "R") {
                        Button(
                            onClick = {
//                          navController.navigate(Screens.)
                            },
                            contentPadding = PaddingValues(30.dp, 10.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xf0f0f0fc)
                            )
                        ) {
                            Text(text = "Thoát")
                        }
                        Button(
                            onClick = {
                                shopViewModel.updateScreenState("U")
                                screenState = shopViewModel.screenState.value
                            },
                            contentPadding = PaddingValues(30.dp, 10.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF46BEF8)
                            )
                        ) {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(text = "Chỉnh sửa",
                                color = Color.White)
                        }
                    } else {
                        if (screenState == "C") {
                            Button(
                                onClick = { /*TODO*/ },
                                contentPadding = PaddingValues(30.dp, 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xf0f0f0fc)
                                )
                            ) {
                                Text(text = "Hủy")
                            }

                            Button(
                                onClick = {
                                    shopViewModel.createDetailShop(context)
                                },
                                contentPadding = PaddingValues(30.dp, 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xFF46BEF8)
                                )
                            ) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.White
                                )
                                Text(text = "Tạo khuyến mãi",
                                    color = Color.White)
                            }

                        } else {

                            Button(
                                onClick = { /*TODO*/ },
                                contentPadding = PaddingValues(30.dp, 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xf0f0f0fc)
                                )
                            ) {
                                Text(text = "Hủy")
                            }

                            Button(
                                onClick = {shopViewModel.updateDetailShop(context)},
                                contentPadding = PaddingValues(30.dp, 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xFF46BEF8)
                                )
                            ) {
                                Icon(
                                    Icons.Filled.Save,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.White
                                )
                                Text(text = "Lưu khuyến mãi",
                                    color = Color.White)
                            }

                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun Preview(){
}