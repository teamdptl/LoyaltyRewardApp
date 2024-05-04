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
import com.example.loyaltyrewardapp.components.ImagePicker
import com.example.loyaltyrewardapp.components.MainBackgroundScreen

class DetailCouponActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                CURCouponScreen("", "", 0, 0, "", "R")
            }
        }
    }
}

@Composable
fun CURCouponScreen(
    couponName: String,
    description: String,
    point: Int,
    timeExpire: Int,
    imageUri: String,
    screenState: String
){
    MainBackgroundScreen(title = "Thêm ưu đãi") {
        Column(
            Modifier
                .padding(40.dp, 30.dp)
                .background(Color.White)
        ) {
            LabelTextField(label = "Tên ưu đãi", fieldValue = couponName, numOfRow = 1, screenState = screenState)
            Spacer(modifier = Modifier.size(10.dp))
            LabelTextField(label = "Mô tả", fieldValue = description, numOfRow = 4, screenState = screenState)
            Spacer(modifier = Modifier.size(10.dp))
            LabelTextField(label = "Điểm sử dụng", fieldValue = point.toString(), screenState = screenState)
            Spacer(modifier = Modifier.size(10.dp))
            LabelTextField(label = "Hết hạn sau (tháng)", fieldValue = timeExpire.toString(), screenState = screenState)
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

                ImagePicker(text = "Choose Image", imageUri)
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
            GroupButtonAction(screenState = "R")
        }
    }
}

@Composable
fun GroupButtonAction(screenState: String){
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        if (screenState == "R") {
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(30.dp, 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(240, 240, 240),
                    disabledBackgroundColor = Color(240f, 240f, 240f, 0.4f)
                )
            ) {
                Text(text = "Thoát")
            }
//            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(30.dp, 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(64f, 169f, 220f, 0.99f),
                    disabledBackgroundColor = Color(64f, 169f, 220f, 0.4f)
                )
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = "Chỉnh sửa")
            }
        } else {
            if (screenState == "C") {
                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(30.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(240, 240, 240),
                        disabledBackgroundColor = Color(240f, 240f, 240f, 0.4f)
                    )
                ) {
                    Text(text = "Hủy")
                }

                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(30.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(64f, 169f, 220f, 0.99f),
                        disabledBackgroundColor = Color(64f, 169f, 220f, 0.4f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "Tạo khuyến mãi")
                }

            } else {

                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(30.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(240, 240, 240),
                        disabledBackgroundColor = Color(240f, 240f, 240f, 0.4f)
                    )
                ) {
                    Text(text = "Hủy")
                }

                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(30.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(64f, 169f, 220f, 0.99f),
                        disabledBackgroundColor = Color(64f, 169f, 220f, 0.4f)
                    )
                ) {
                    Icon(
                        Icons.Filled.Save,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "Lưu")
                }

            }
        }
    }
}

@Composable
fun LabelTextField(label: String, fieldValue: String, numOfRow : Int = 1, screenState: String){
    var text by remember { mutableStateOf(fieldValue) }
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
            value = text,
            onValueChange = {text = it},
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
            readOnly = screenState == "R"
        )
    }
}

@Preview
@Composable
fun CURCouponPreview(){
    CURCouponScreen("", "", 0, 0, "", "R")
}

