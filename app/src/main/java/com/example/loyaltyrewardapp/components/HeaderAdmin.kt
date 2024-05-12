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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.ShopDaily
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.Visited
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor
import com.example.loyaltyrewardapp.ui.theme.OrangeColor
import com.google.firebase.auth.FirebaseUser

@Composable
fun HeaderAdmin(
    firebaseData: FirebaseUser,
    user: User,
    shopDaily: ShopDaily,
    visitedManagers: List<Visited>
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = firebaseData.displayName.toString(),
                            color = TextBlackColor,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(text = "Chủ cửa hàng", color = Color.Gray)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically,) {
                    Spacer(modifier = Modifier.width(8.dp))
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Red,
                                modifier = Modifier.offset(5.dp, 0.dp)
                            ) {
//                                Text(text = "0", color = Color.White)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Badge, // Replace with your bell icon resource
                            contentDescription = "Bell icon",
                            tint = TextBlackColor,
                        )
                    }
                }
            }
            Text(text = "Thống kê hôm nay")
            CardHeader(firebaseData, user, shopDaily, visitedManagers)


        }
    }
}

@Composable
fun CardHeader(
    firebaseData: FirebaseUser,
    user: User,
    shopDaily: ShopDaily,
    visitedManagers: List<Visited>
) {
    Column(
        modifier = Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, start = 35.dp, end = 35.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Đặt cách đều giữa các thành phần
        ) {
            // Cặp thứ nhất Icon và Column
            Row(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.QrCode,
                    contentDescription = "",
                    tint = OrangeColor,
                    modifier = Modifier.size(30.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Đã quét cho")
                    Text(text = visitedManagers.size.toString() + " users",fontWeight = FontWeight.W500,)
                }
            }
            // Cặp thứ hai Icon và Column
            Row(
            ) {
                Icon(
                    imageVector = Icons.Filled.CurrencyExchange,
                    contentDescription = "",
                    tint = OrangeColor,
                    modifier = Modifier.size(30.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Điểm đã cấp")
                    Text(text = shopDaily.diemDaCap.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        maxLines = 1,
                        )
                }

            }
        }
    }
}