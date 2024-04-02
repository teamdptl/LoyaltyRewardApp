package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.components.CompaniesItem
import com.example.loyaltyrewardapp.components.RewardItemPreview
import com.example.loyaltyrewardapp.components.SquareImage
import com.example.loyaltyrewardapp.data.ShopProvider
import com.example.loyaltyrewardapp.ui.theme.GrayMap
import com.example.loyaltyrewardapp.ui.theme.MainColor
import com.example.loyaltyrewardapp.ui.theme.OrangeColor
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor
import com.example.loyaltyrewardapp.ui.theme.Yellow
import kotlinx.coroutines.launch

class DetailCompanyActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = androidx.compose.material.MaterialTheme.colors.background
            ) {
                DetailCompany()
            }
        }
    }

}

enum class SelectedItem {
    First, Second, Third
}
@Preview
@Composable
fun DetailCompany() {
    val company = remember { ShopProvider.shop }
    var selectedItem by remember { mutableStateOf(SelectedItem.First) }
    val companies = remember { ShopProvider.shopList }





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
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Row(){
                    IconButton(
                        onClick = { /*TODO*/ },
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
                    Row (horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(all = 10.dp)){
                        Text(text = "Điểm 10", textAlign = TextAlign.Center, color = TextBlackColor, fontSize = 12.sp, modifier = Modifier
                            .width(100.dp)
                            .background(color = Yellow, shape = RoundedCornerShape(10.dp))
                            .padding(10.dp))
                    }

                }

//            SquareImage(
//                item = company,
//                pictureUrlProperty = { it.pictureUrl },
//                size = 160.dp
//            )

            }
            Text(
                text = "Cửa hàng của Duy",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )
            Text(
                text = "130 Nguyễn Văn Cừ",
                maxLines = 1,
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
                    Text("Ưu đãi",   style = MaterialTheme.typography.bodyMedium,
                        color = OrangeColor, fontWeight = FontWeight.SemiBold)


                }
                TextButton(onClick = {
                    selectedItem = SelectedItem.Second

                }
                ) {
                    Text("Dịch vụ",  style = MaterialTheme.typography.bodyMedium,
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 3.dp,

                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        items(
                            items = companies,
                            itemContent = {
                                DetailRewardPreview(

                                )
                            }
                        )
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(1),
                        verticalItemSpacing = 5.dp,

                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                    ) {
                        items(
                            items = companies,
                            itemContent = {
                                RewardItemPreview(

                                )
                            }
                        )
                    }

                }

                SelectedItem.Third -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


                        Spacer(modifier = Modifier
                            .weight(1.15f))
                        Spacer(modifier = Modifier
                            .weight(1.15f))
                        Spacer(
                            modifier = Modifier
                                .weight(0.7f)
                                .height(1.dp)
                                .background(color = OrangeColor)
                        )
                    }
                    Text("Nội dung cho Chi tiết")
                }

            }
        }



}

