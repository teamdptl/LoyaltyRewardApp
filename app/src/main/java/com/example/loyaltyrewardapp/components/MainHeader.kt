package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.navigation.Screens
import com.example.loyaltyrewardapp.ui.theme.MainColor
import com.example.loyaltyrewardapp.ui.theme.SelectedColor
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor
import com.example.loyaltyrewardapp.ui.theme.Yellow
import com.lightspark.composeqr.QrCodeView
import com.example.loyaltyrewardapp.ui.theme.Typography
import com.google.firebase.auth.FirebaseUser


@Composable
fun UserCard(firebaseData: FirebaseUser, user: User) {
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
                data = user.qr?:"Error",
                modifier = Modifier.size(140.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp), verticalArrangement = Arrangement.Center) {
                Text(text = "Thẻ thành viên", style = Typography.titleMedium,
                    color = MainColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
//                Column(modifier = Modifier.padding(top = 4.dp)){
//                    Text(text = "Mã thành viên: ", style = Typography.displaySmall)
//                    Text(text = user.qr?:"Error", style = Typography.bodySmall,  color=Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
//                }
                Column(modifier = Modifier.padding(top = 8.dp)){
                    Text(text = "Họ tên:", style = Typography.displaySmall, modifier = Modifier.padding(bottom = 5.dp))
                    Text(text = firebaseData.displayName?:"Không có tên", style = Typography.bodyMedium, color=Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Column(modifier = Modifier.padding(top = 8.dp)){
                    Text(text = "Điện thoại: ", style = Typography.displaySmall, modifier = Modifier.padding(bottom = 5.dp))
                    Text(text = firebaseData.phoneNumber?:"Không có số", style = Typography.bodyMedium, color=Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Text(text = "Ngày tham gia: ${user.created_at.split(" ")[0]}", style = Typography.displaySmall, fontSize = 11.sp, fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 8.dp))
            }
        }

    }
}

@Composable
fun ApplyRewardButton(navController: NavController){
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 6.dp)) {

        Button(onClick = { navController.navigate(Screens.CouponScreen.name) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.White)) {
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
//        if (!removable){
//
//        }
//        else {
//            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = SelectedColor, contentColor = SelectedColor)) {
//                Icon(
//                    painter = painterResource(id = R.drawable.discount_icon),
//                    contentDescription = null,
//                    modifier = Modifier.size(16.dp),
//                    tint = Color.Black
//                )
//                Spacer(modifier = Modifier.width(6.dp))
//                Text(
//                    text = "Đã chọn 1 khuyến mãi",
//                    color = Color.Black,
//                    style = MaterialTheme.typography.bodySmall
//                )
//                Spacer(modifier = Modifier.width(6.dp))
//                Icon(Icons.Outlined.Cancel, contentDescription = null,
//                    modifier = Modifier.size(16.dp),
//                    tint = Color.Red)
//            }
//        }

    }

}

@Composable
fun MainUserHeader(navController: NavController, firebaseData: FirebaseUser, user: User){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(330.dp)){
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
                        Text(text = firebaseData.displayName!!, color = TextBlackColor, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 4.dp))
                        Text(text = "Khách hàng", color = Color.Gray)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Xem điểm", textAlign = TextAlign.Center, color = TextBlackColor, fontSize = 13.sp, modifier = Modifier
                        .width(100.dp)
                        .background(color = Yellow, shape = RoundedCornerShape(10.dp))
                        .padding(vertical = 10.dp, horizontal = 6.dp)
                        .clickable{
                            navController.navigate(Screens.MyPointScreen.name)
                        })
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
            UserCard(firebaseData, user)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HeaderPreview(){
//    MainUserHeader()
//}