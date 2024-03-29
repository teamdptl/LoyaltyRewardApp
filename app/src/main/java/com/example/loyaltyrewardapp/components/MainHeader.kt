package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.ui.theme.MainColor
import com.example.loyaltyrewardapp.ui.theme.SelectedColor
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor
import com.example.loyaltyrewardapp.ui.theme.Yellow
import com.lightspark.composeqr.QrCodeView
import com.example.loyaltyrewardapp.ui.theme.Typography


@Composable
fun UserCard() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 4.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(12.dp)
            .padding(horizontal = 12.dp)) {
        Text(text = "Đưa mã này cho nhân viên để tích điểm", style = Typography.bodySmall)
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)){
            QrCodeView(
                data = "2500323123123123|321312",
                modifier = Modifier.size(140.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp), verticalArrangement = Arrangement.Center) {
                Text(text = "Thẻ thành viên", style = Typography.titleMedium,
                    color = MainColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
                Column(modifier = Modifier.padding(top = 4.dp)){
                    Text(text = "Họ tên:", style = Typography.displaySmall)
                    Text(text = "Huỳnh Khánh Duy", style = Typography.bodyMedium, color=Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Column(modifier = Modifier.padding(top = 4.dp)){
                    Text(text = "Điện thoại: ", style = Typography.displaySmall)
                    Text(text = "01234567", style = Typography.bodyMedium,  color=Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Text(text = "Ngày tham gia: 24/12/2023", style = Typography.bodySmall, fontSize = 11.sp,
                    modifier = Modifier.padding(top = 6.dp))
            }
        }

    }
}

@Composable
fun ApplyRewardButton(removable: Boolean = false){
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 6.dp)) {
        if (!removable){
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.White)) {
                Icon(
                    painter = painterResource(id = R.drawable.discount_icon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Sử dụng khuyến mãi",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        else {
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = SelectedColor, contentColor = SelectedColor)) {
                Icon(
                    painter = painterResource(id = R.drawable.discount_icon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Đã chọn 1 khuyến mãi",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(Icons.Outlined.Cancel, contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Red)
            }
        }

    }

}

@Composable
fun MainHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)){
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
                            Badge(containerColor = Color.Red, modifier = Modifier.offset(5.dp, 0.dp)) {
                                Text(text = "0", color = Color.White)
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
            UserCard()
            ApplyRewardButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview(){
    MainHeader()
}