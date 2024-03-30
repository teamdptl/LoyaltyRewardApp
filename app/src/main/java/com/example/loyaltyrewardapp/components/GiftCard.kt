package com.example.loyaltyrewardapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun GiftCardItem(){
    BoxWithConstraints {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
        ){
            Image(painter = painterResource(id = R.drawable.ticket),
                contentDescription = "",
                Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    ,
                contentScale = ContentScale.FillBounds
            )

            Row(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp, 5.dp, 18.dp, 5.dp)
            ){
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(6.5f)
                        .padding(5.dp, 5.dp, 10.dp, 5.dp)
                ) {

                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(brush = Brush.linearGradient(listOf(Color(0xFFFD883B), Color.Red)), fontSize = 12.sp, fontWeight = FontWeight.Bold)){
                                append("Cửa hàng sửa xe Thanh Phúc")
                            }
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(brush = Brush.linearGradient(listOf(Color(0xFFFD883B), Color.Red)), fontSize = 20.sp, fontWeight = FontWeight.W900)){
                                append("Miễn phí thay nhớt")
                            }
//                            withStyle(SpanStyle(brush = Brush.linearGradient(listOf(Color(0xFFFD883B), Color.Red)), fontSize = 28.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily.Serif)){
//                                append("Off")
//                            }
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif

                    )

//                    Text("Dành cho những khách hàng thân thiết đã sủ dụng dịch vụ của chúng tôi và đủ điều kiện để được miễn phí thay nhớt",
//                        modifier = Modifier.alpha(0.4f).padding(5.dp),
//                        overflow = TextOverflow.Ellipsis,
//                        maxLines = 2)

                    Spacer(modifier = Modifier.size(6.dp))

                    Text(
                        text = "Miễn phí 1 chai nhớt castol power khi đổi 5 điểm tiêu dùng",
                        fontSize = 10.sp,
                        color = Color.Black.copy(alpha = 0.7f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(fontStyle = FontStyle.Italic, color = Color.Black)){
                                append("Hạn dùng: 20/2/2024")
                            }
                        },
                        fontSize = 10.sp,

                    )

//                    Text(buildAnnotatedString {
//                        append("Hết hạn: ")
//                        withStyle(SpanStyle(color = Color.Black.copy(alpha = 0.4f))){
//                            append("2 tháng kể từ ngày quy đổi")
//                        }
//                    })
                }

                Row(
                    Modifier
                        .fillMaxHeight()
                        .weight(3.0f)
                        .padding(10.dp, 5.dp, 5.dp, 5.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.background),
                        contentDescription = "Lego logo",
                        Modifier.fillMaxSize()
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Fit,)
                }
            }
        }
    }

}




@Preview
@Composable
fun TestCouponDiscount(){
    GiftCardItem()
}