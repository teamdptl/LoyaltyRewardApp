package com.example.loyaltyrewardapp.screens.manager

import android.net.Uri
import android.os.Bundle
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
import androidx.compose.material.ButtonColors
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                CURCouponScreen()
            }
        }
    }
}

@Composable
fun CURCouponScreen(couponViewModel : AdminCURCouponViewModel = AdminCURCouponViewModel()){
    val coupon by couponViewModel.coupon
    val screenState by couponViewModel.screenState
    var title by remember{mutableStateOf("")}

    title = if (screenState == "R") {
        "Thông tin ưu đãi"
    }else{
        if (screenState == "C") {
            "Thêm ưu đãi"
        }else{
            "Cập nhật ưu đãi"
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
                    fieldValue = co.couponName,
                    numOfRow = 1, {couponViewModel.updateCouponName(it)},
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Mô tả",
                    fieldValue = co.description,
                    numOfRow = 4, {couponViewModel.updateDescription(it)},
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Điểm sử dụng",
                    fieldValue = co.point.toString(),
                    onValueChange = {couponViewModel.updatePoint(it.toInt())},
                    screenState = screenState)
                Spacer(modifier = Modifier.size(10.dp))

                LabelTextField(label = "Hết hạn sau (tháng)",
                    fieldValue = co.timeExpire.toString(),
                    onValueChange = {couponViewModel.updateTimeExpire(it.toInt())},
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

                    ImagePicker(text = "Choose Image", co.imageUri)
                }

                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = true,
                        onCheckedChange = {})
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
                GroupButtonAction(screenState = screenState,
                    "Thêm khuyến mãi",
                    "Lưu thay đổi",
                    "Cập nhật")
            }

        }
    }
}

@Composable
fun GroupButtonAction(screenState: String, textCreate: String, textUpdate: String, textView: String){
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        if (screenState == "R") {
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(30.dp, 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xf0f0f0fc)
                )
            ) {
                Text(text = "Thoát")
            }
            Button(
                onClick = { /*TODO*/ },
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
                Text(text = textView,
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
                    onClick = { /*TODO*/ },
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
                    Text(text = textCreate,
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
                    onClick = { /*TODO*/ },
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
                    Text(text = textUpdate,
                        color = Color.White)
                }

            }
        }
    }
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
            onValueChange = onValueChange,
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
    CURCouponScreen(couponViewModel)
}

