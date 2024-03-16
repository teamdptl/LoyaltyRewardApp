package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.ui.theme.MainColor
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor
import com.example.loyaltyrewardapp.ui.theme.Yellow
import com.lightspark.composeqr.QrCodeView


@Composable
fun userCard() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 4.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(12.dp)
            .padding(horizontal = 12.dp)) {
        Text(text = "Đưa mã này cho nhân viên để tích điểm", fontSize = 12.sp)
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)){
            QrCodeView(
                data = "2500323123123123|321312",
                modifier = Modifier.size(140.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(text = "Thẻ thành viên", fontSize = 14.sp,
                    fontWeight = FontWeight.Medium, color = MainColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
                Column(modifier = Modifier.padding(top = 8.dp)){
                    Text(text = "Họ tên:", fontWeight = FontWeight.Medium, fontSize = 12.sp, modifier = Modifier.padding(bottom = 4.dp))
                    Text(text = "Huỳnh Khánh Duy", fontWeight = FontWeight.Light)
                }
                Column(modifier = Modifier.padding(top = 8.dp)){
                    Text(text = "Điện thoại: ", fontWeight = FontWeight.Medium, fontSize = 12.sp, modifier = Modifier.padding(bottom = 4.dp))
                    Text(text = "01234567", fontWeight = FontWeight.Light)
                }
                Text(text = "Ngày tham gia: 24/12/2023", fontSize = 10.sp, textAlign = TextAlign.Right, modifier = Modifier.padding(top = 10.dp))
            }
        }
    }
}

@Composable
fun MainHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)){
        Image(painter = painterResource(id = R.drawable.background), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Column{
            Row(modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(painterResource(id = R.drawable.user), contentDescription = null, modifier = Modifier.size(40.dp))
                    Column (modifier = Modifier.padding(start = 8.dp)) {
                        Text(text = "Huỳnh Khánh Duy", color = TextBlackColor, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))
                        Text(text = "Khách hàng", color = Color.Gray)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Điểm: 0", textAlign = TextAlign.Center, color = TextBlackColor, modifier = Modifier
                        .width(80.dp)
                        .background(color = Yellow, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    BadgedBox(
                        badge = {
                            Badge(backgroundColor = Color.Red) {
                                Text(text = "19", color = Color.White)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Notifications, // Replace with your bell icon resource
                            contentDescription = "Bell icon",
                            tint = TextBlackColor,
                        )
                    }
                }
            }
            userCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview(){
    MainHeader()
}