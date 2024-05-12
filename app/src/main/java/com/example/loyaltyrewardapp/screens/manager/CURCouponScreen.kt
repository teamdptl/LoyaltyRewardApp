package com.example.loyaltyrewardapp.screens.manager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
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

class DetailCouponActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
//                CURCouponScreen()
            }
        }
    }
}

@Composable
fun CURCouponScreen(navController: NavController = rememberNavController(), couponId: String, screen: String = "R", couponViewModel : AdminCURCouponViewModel = AdminCURCouponViewModel()){

    var coupon by remember {couponViewModel.coupon}
    var screenState by remember {couponViewModel.screenState}
    var point by remember { mutableStateOf<String>(couponViewModel.coupon.value?.require_point.toString())}
    var expiredAt by remember { mutableStateOf<String>(couponViewModel.coupon.value?.expired_after.toString())}
    var title by remember{mutableStateOf("")}
    val context = LocalContext.current
    var edited: Boolean = false

    Log.d("CUR Coupon Screen", "render screen")
    LaunchedEffect(key1 = null){
        couponId.let{
            couponViewModel.getDetailCoupon(screen, it)
            point = couponViewModel.coupon.value?.require_point.toString()
            expiredAt = couponViewModel.coupon.value?.expired_after.toString()
            Log.d("Loading", "Dang load du lieu")
        }
    }

    if(coupon == null){
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
            coupon?.let { co ->
                LabelTextField(label = "Tên ưu đãi",
                    fieldValue = co.name,
                    numOfRow = 1,
                    {
                        if (!edited){
                            edited = true
                        }
                        couponViewModel.updateCouponName(it)
                        coupon = coupon?.copy(name = it)
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Mô tả",
                    fieldValue = co.description,
                    numOfRow = 4,
                    {
                        if (!edited){
                            edited = true
                        }
                        couponViewModel.updateDescription(it)
                        coupon = coupon?.copy(description = it)
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Điểm sử dụng",
                    fieldValue = point.toString(),
                    onValueChange =
                    {
                        point = it
                        if (!edited){
                            edited = true
                        }
                        if (point.isBlank()){
                            couponViewModel.updatePoint(-1)
                            coupon = coupon?.copy(require_point = -1)
                        }else{
                            couponViewModel.updatePoint(it.toIntOrNull()?: -1)
                            coupon = coupon?.copy(require_point = it.toIntOrNull()?: -1)
                        }
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Hết hạn sau (tháng)",
                    fieldValue = expiredAt,
                    onValueChange =
                    {
                        expiredAt = it
                        if (!edited){
                            edited = true
                        }
                        if (expiredAt.isBlank()){
                            couponViewModel.updateTimeExpire(0)
                            coupon = coupon?.copy(expired_after = 0)
                        }else{
                            couponViewModel.updateTimeExpire(it.toIntOrNull()?: 0)
                            coupon = coupon?.copy(expired_after = it.toIntOrNull()?: 0)
                        }
                    },
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Text(
                        text = "Hình ảnh",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    ImagePicker(updateUri = {
                        couponViewModel.updateImageUri(it)
                        if (!edited){
                            edited = true
                        }
                                            },
                        text = "Choose Image", co.icon, screenState)

                }

                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = co.is_active,
                        enabled = screenState != "R",
                        onCheckedChange = {
                            if (!edited){
                                edited = true
                            }
                            couponViewModel.updateIsActive(it)
                            coupon = coupon?.copy(is_active = it)
                        })
                    Text(
                        text = "Đang hoạt động",
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
                                navController.popBackStack()
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
                                couponViewModel.updateScreenState("U")
                                screenState = couponViewModel.screenState.value
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
                                onClick = { navController.popBackStack() },
                                contentPadding = PaddingValues(30.dp, 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xf0f0f0fc)
                                )
                            ) {
                                Text(text = "Hủy")
                            }

                            Button(
                                onClick = {
                                    couponViewModel.createDetailCoupon(context)
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
                                onClick = { navController.popBackStack() },
                                contentPadding = PaddingValues(30.dp, 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xf0f0f0fc)
                                )
                            ) {
                                Text(text = "Hủy")
                            }

                            Button(
                                onClick = {couponViewModel.updateDetailCoupon(context, edited)},
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

@Composable
fun GroupButtonAction(navController: NavController = rememberNavController(), viewModel: ViewModel, typeScreen: String, textCreate: String, textUpdate: String, textView: String){


}


@Composable
fun LabelTextField(label: String, fieldValue: String, numOfRow : Int = 1, onValueChange : (String) -> Unit, screenState: String){
    Column {
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        OutlinedTextField(
            value = fieldValue,
            onValueChange = { onValueChange(it)},
            minLines = numOfRow,
            maxLines = numOfRow,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textStyle = TextStyle(
                fontSize=14.sp,
                color = Color.Black,
            ),
            shape = RoundedCornerShape(15.dp),
            readOnly = screenState == "R",
            enabled =  screenState != "R"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CURCouponPreview(){
    val couponViewModel = remember {AdminCURCouponViewModel()}
    CURCouponScreen(couponId = "663a4e93d3b422b0fe0e235b")
}

