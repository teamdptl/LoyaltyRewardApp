package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import com.example.loyaltyrewardapp.ui.theme.MainColor
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.model.User
import com.google.firebase.auth.FirebaseUser

@Composable
fun ShopOfManagerItem(
    user: User
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp)
            .background(color = Color.White)
    ) {
        // Bên trái: Hình ảnh
        SquareOnlineImage(
            url = user.shop?.logo.toString(),
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(5.dp)
                .clip(RoundedCornerShape(8.dp)),

        )
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(top = 5.dp),
        ) {
            Text(
                text = user.shop?.name.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                maxLines = 1,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = user.shop?.address.toString(),
                fontSize = 10.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                maxLines = 1,
            )
            Text(
                text =  user.shop?.description.toString(),
                fontSize = 10.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, bottom = 3.dp),
                maxLines = 1,
            )
            Row(modifier = Modifier.padding(start = 8.dp),) {
                Box(
                    modifier = Modifier
                        .background(color = MainColor)
                        .clip(
                            RoundedCornerShape(
                                topStart = 8.dp,
                                bottomStart = 8.dp,
                                topEnd = 8.dp,
                                bottomEnd = 8.dp
                            )
                        )
                ) {
                    Text(
                        text = "Quản lý cửa hàng",
                        modifier = Modifier
                            .padding(8.dp),
                        color = Color.White,
                        maxLines = 1,
                    )
                }
            }

        }
    }
}