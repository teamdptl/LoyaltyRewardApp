package com.example.loyaltyrewardapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.loyaltyrewardapp.components.SquareOnlineImage
import com.example.loyaltyrewardapp.data.viewmodel.CouponUserQRViewModel
import com.example.loyaltyrewardapp.ui.theme.OrangeColor
import com.lightspark.composeqr.QrCodeView


@Composable
fun UserCouponQR(navController: NavHostController, couponId: String?, viewModel: CouponUserQRViewModel = CouponUserQRViewModel()) {
    val coupon by remember {
        viewModel.couponUserQR
    }

    LaunchedEffect(null) {
        couponId?.let {
            viewModel.getCouponUserQR(couponId)
            Log.d("Loading", "Dang load du lieu detail user coupon")
            Log.d("lay id detail coupon", couponId)
        }
    }

    if(coupon == null){

    }else{
        coupon!!
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SquareOnlineImage(
                        url  = coupon!!.icon,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        )
                    Row() {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            colors = IconButtonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black,
                                disabledContainerColor = Color.Gray,
                                disabledContentColor = Color.Black
                            ),
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(50.dp)
                                .padding(all = 10.dp)
                        ) {
                            Icon(
                                Icons.Filled.ArrowBackIosNew,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .size(20.dp),
                                tint = Color.Black.copy(alpha = 0.6f)
                            )
                        }


                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = coupon?.name?: "Tên coupon",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(0.7f)
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(0.1f)
                    )
                    Text(
                        text = "${coupon?.require_point?: 0} điểm",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        color = OrangeColor,
                        modifier = Modifier.weight(0.2f)
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TextButton(onClick = {
                    }
                    ) {
                        Text(
                            "Mô tả",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OrangeColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(
                        modifier = Modifier
                            .weight(0.7f)
                            .height(1.dp)
                            .background(color = OrangeColor)
                    )
                    Spacer(modifier = Modifier
                        .weight(1.15f))
                    Spacer(modifier = Modifier
                        .weight(1.15f))
                }
                Text(coupon?.description?:"Mô tả", maxLines = 4,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                )
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center

                ) {
                    QrCodeView(
                        data = couponId ?: "Error",
                        modifier = Modifier.size(140.dp)
                    )
                }
            }

        }
    }



}