package com.example.loyaltyrewardapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.components.RewardItemPreview
import com.example.loyaltyrewardapp.components.ServiceItem
import com.example.loyaltyrewardapp.components.SquareImage
import com.example.loyaltyrewardapp.data.SharedObject
import com.example.loyaltyrewardapp.data.ShopProvider
import com.example.loyaltyrewardapp.data.viewmodel.ShopDetailViewModel
import com.example.loyaltyrewardapp.navigation.Screens
import com.example.loyaltyrewardapp.ui.theme.GrayMap
import com.example.loyaltyrewardapp.ui.theme.MainColor
import com.example.loyaltyrewardapp.ui.theme.OrangeColor
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor
import com.example.loyaltyrewardapp.ui.theme.Yellow



enum class SelectedItem {
    First, Second, Third
}


@Composable
fun DetailCompany(navController: NavHostController = rememberNavController(), shopId: String?, viewModel: ShopDetailViewModel = ShopDetailViewModel()) {
    var selectedItem by remember { mutableStateOf(SelectedItem.First) }
    var isAdmin by remember {
        mutableStateOf(false)
    }
    val shop by remember {
        viewModel.shop
    }

    LaunchedEffect(null) {
        if (shopId != null)
            viewModel.getShopDetail(shopId)
        else if (SharedObject.shopId.isNotEmpty()){
            viewModel.getShopDetail(SharedObject.shopId)
            isAdmin = true
        }
    }

    if (shop == null) {

    } else {
        shop!!
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(model = shop?.logo, contentDescription = null, modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                    contentScale = ContentScale.Crop )
                Row(){
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
                            .size(60.dp)
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

                    if (!isAdmin){
                        Row (horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp)){
                            Text(text = "Điểm: ${shop?.your_points?:"0"}", textAlign = TextAlign.Center, color = TextBlackColor, fontSize = 13.sp, fontWeight = FontWeight.Medium, modifier = Modifier
                                .width(100.dp)
                                .background(color = Yellow, shape = RoundedCornerShape(10.dp))
                                .padding(10.dp))
                        }
                    }
                }

//            SquareImage(
//                item = companies,
//                pictureUrlProperty = { it.pictureUrl },
//                size = 160.dp
//            )

            }
            Text(
                text = shop?.name ?: "Tên cửa hàng",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp)
            )
            Text(
                text = shop?.address?:"Địa chỉ",
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                color = GrayMap,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 2.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TextButton(onClick = {
                    selectedItem = SelectedItem.First
                }
                ) {
                    Text("Ưu đãi", style = MaterialTheme.typography.bodyMedium,
                        color = OrangeColor, fontWeight = FontWeight.SemiBold)
                }
                TextButton(onClick = {
                    selectedItem = SelectedItem.Second

                }
                ) {
                    Text("Dịch vụ", style = MaterialTheme.typography.bodyMedium,
                        color = OrangeColor, fontWeight = FontWeight.SemiBold)
                }
                TextButton(onClick = {
                    selectedItem = SelectedItem.Third

                }
                ) {
                    Text("Chi tiết",  style = MaterialTheme.typography.bodyMedium,
                        color = OrangeColor, fontWeight = FontWeight.SemiBold)
                }
            }
            // Content
            when (selectedItem) {
                SelectedItem.First -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                        ,
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            verticalItemSpacing = 3.dp,

                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                        ) {
                            items(
                                items = shop?.coupons?: listOf(),
                                itemContent = {
                                    InfoRewardCard(
                                        name = it.name,
                                        url = it.icon,
                                        point = it.require_point,
                                        description = it.description,
                                        isAdmin = isAdmin,
                                        onClick = {
                                            navController.navigate(Screens.AdminReadCoupon.name + "/${it._id}")
                                        },
                                        onEdit = {
                                            navController.navigate(Screens.AdminUpdateCoupon.name + "/${it._id}")
                                        },
                                        onDelete = {

                                        }
                                    )
                                }
                            )
                        }
                        if (isAdmin) {
                            // Hiển thị icon cộng khi là admin và selectedItem là First hoặc Second
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(bottom = 20.dp, end = 16.dp)
                            ) {
                                Spacer(modifier = Modifier.weight(1f)) // Spacer bên trái để căn chỉnh
                                AddIconButton(
                                    onClick = {
                                              navController.navigate(Screens.AdminCreateCoupon.name + "/${shopId}")
                                    },
                                    isAdmin = isAdmin
                                )
                            }
                        }
                    }




                }

                SelectedItem.Second -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier
                            .weight(1f))
                        Spacer(
                            modifier = Modifier
                                .weight(0.7f)
                                .height(1.dp)
                                .background(color = OrangeColor)
                        )
                        Spacer(modifier = Modifier
                            .weight(1f))
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(1),
                            verticalItemSpacing = 5.dp,

                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                        ) {
                            items(
                                items = shop?.services?: listOf(),
                                itemContent = {
                                    ServiceItem(
                                        name = it.name,
                                        description = it.description,
                                        isAdmin = isAdmin,
                                        onClick = {
                                            navController.navigate(Screens.AdminReadService.name + "/${it._id}")
                                        },
                                        onEdit = {
                                            navController.navigate(Screens.AdminUpdateService.name + "/${it._id}")
                                        },
                                        onDelete = {

                                        }
                                    )
                                }
                            )
                        }
                        if (isAdmin) {
                            // Hiển thị icon cộng khi là admin và selectedItem là First hoặc Second
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(bottom = 20.dp, end = 16.dp)
                            ) {
                                Spacer(modifier = Modifier.weight(1f)) // Spacer bên trái để căn chỉnh
                                AddIconButton(
                                    onClick = {
                                        navController.navigate(Screens.AdminCreateService.name + "/${shopId}")
                                    },
                                    isAdmin = isAdmin
                                )
                            }
                        }
                    }

                }

                SelectedItem.Third -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp, vertical = 10.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


//                        Spacer(modifier = Modifier
//                            .weight(1.15f))
//                        Spacer(modifier = Modifier
//                            .weight(1.15f))
//                        Spacer(
//                            modifier = Modifier
//                                .weight(0.7f)
//                                .height(1.dp)
//                                .background(color = OrangeColor)
//                        )

                        Text(shop?.description?:"Mô tả")
                    }

                }

            }
        }
    }

}

@Composable
fun AddIconButton(
    onClick: () -> Unit,
    isAdmin: Boolean
) {
    if (isAdmin) {
        IconButton(
            onClick = onClick,
            colors = IconButtonColors(
                containerColor = MainColor,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(50.dp)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}
