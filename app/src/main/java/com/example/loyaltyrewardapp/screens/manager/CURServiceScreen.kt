package com.example.loyaltyrewardapp.screens.manager

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.ImagePicker
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURCouponViewModel
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURServiceViewModel

@Composable
fun CURServiceScreen(navController: NavController = rememberNavController(), serviceId: String, screen: String = "R", serviceViewModel : AdminCURServiceViewModel = AdminCURServiceViewModel()){

    var service by remember {serviceViewModel.service}
    var screenState by remember {serviceViewModel.screenState}
    var point by remember { mutableStateOf<String>(serviceViewModel.service.value?.points_reward.toString()) }
    var period by remember { mutableStateOf<String>(serviceViewModel.service.value?.period.toString()) }
    var title by remember{ mutableStateOf("") }
    val context = LocalContext.current

    Log.d("CUR Coupon Screen", "render screen")
    LaunchedEffect(key1 = null){
        serviceId.let{
            serviceViewModel.getDetailService(screen, it)
            point = serviceViewModel.service.value?.points_reward.toString()
            period = serviceViewModel.service.value?.period.toString()
            Log.d("Loading", "Dang load du lieu")
        }
    }

    if(service == null){
        Log.d("Loading", "Chua co du lieu")
    }else{
        title = if (screenState == "R") {
            "Thông tin ưu đãi"
        }else{
            if (screenState == "C") {
                "Thêm ưu đãi"
            }else{
                "Cập nhật ưu đãi"
            }
        }
    }


    MainBackgroundScreen(title = title) {
        Column(
            Modifier
                .padding(40.dp, 30.dp)
                .background(Color.White)
        ) {
            service?.let { co ->
                LabelTextField(label = "Tên dịch vụ",
                    fieldValue = co.name,
                    numOfRow = 1,
                    {
                        if (!serviceViewModel.isEdited.value){
                            serviceViewModel.updateIsEdited(true)
                        }
                        serviceViewModel.updateServiceName(it)
                        service = service?.copy(name = it)
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Mô tả",
                    fieldValue = co.description,
                    numOfRow = 4,
                    {
                        if (!serviceViewModel.isEdited.value){
                            serviceViewModel.updateIsEdited(true)
                        }
                        serviceViewModel.updateServiceDescription(it)
                        service = service?.copy(description = it)
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Điểm thưởng (mỗi lần)",
                    fieldValue = point.toString(),
                    onValueChange =
                    {
                        point = it
                        if (!serviceViewModel.isEdited.value){
                            serviceViewModel.updateIsEdited(true)
                        }
                        if (point.isBlank()){
                            serviceViewModel.updateServicePoint(-1)
                            service = service?.copy(points_reward = -1)
                        }else{
                            serviceViewModel.updateServicePoint(it.toIntOrNull()?: -1)
                            service = service?.copy(points_reward = it.toIntOrNull()?: -1)
                        }
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                if (service?.should_notification == true){
                    LabelTextField(label = "Thông báo định kì",
                        fieldValue = period,
                        onValueChange =
                        {
                            period = it
                            if (!serviceViewModel.isEdited.value){
                                serviceViewModel.updateIsEdited(true)
                            }
                            if (period.isBlank()){
                                serviceViewModel.updateServicePeriod(0)
                                service = service?.copy(period = 0)
                            }else{
                                serviceViewModel.updateServicePeriod(it.toIntOrNull()?: 0)
                                service = service?.copy(period = it.toIntOrNull()?: 0)
                            }
                        },
                        screenState = screenState)
                }


                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = co.should_notification,
                        enabled = screenState != "R",
                        onCheckedChange = {
                            if (!serviceViewModel.isEdited.value){
                                serviceViewModel.updateIsEdited(true)
                            }
                            serviceViewModel.updateServiceNotify(it)
                            service = service?.copy(should_notification = it)
                        })
                    Text(
                        text = "Phải thông báo định kỳ",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))
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
                                serviceViewModel.updateScreenState("U")
                                screenState = serviceViewModel.screenState.value
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
                                    serviceViewModel.createDetailService(context)
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
                                Text(text = "Tạo dịch vụ",
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
                                onClick = {serviceViewModel.updateDetailService(context)},
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
                                Text(text = "Lưu dịch vụ",
                                    color = Color.White)
                            }

                        }
                    }
                }
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun CURServicePreview(){
    val couponViewModel = remember { AdminCURCouponViewModel() }
    CURCouponScreen(couponId = "663a4e93d3b422b0fe0e235b")
}