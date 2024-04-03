package com.example.loyaltyrewardapp.screens

import androidx.compose.ui.graphics.Color
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.ui.theme.PrimaryColor
import com.lightspark.composeqr.DotShape
import com.lightspark.composeqr.QrCodeView

@Composable
fun ScanQRContent(){
    Column(
        Modifier
            .padding(30.dp, 30.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(Modifier.padding(30.dp, 30.dp)) {
            Text(text = "Hãy quét mã QR của khách hàng để tích điểm cho họ",
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        Column(Modifier.wrapContentSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            val containQRWidth = 300.dp
            val containQRHeight = 300.dp
            RoundedSquareWithKeepCornerBorders(containQRWidth, containQRHeight) {
                val qrSize = 220.dp
                QrCodeView(
                    data = "2500323123123123|321312",
                    modifier = Modifier.size(qrSize)
                        .offset((containQRWidth - qrSize)/2, (containQRHeight - qrSize)/2),
                    dotShape = DotShape.Circle
                )
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RoundedSquareWithKeepCornerBorders(
    width: Dp = 300.dp,
    height: Dp = 400.dp,
    cornerKeepLength: Dp = 80.dp,
    cornerRadius: Dp = 20.dp,
    borderSize: Dp = 4.dp,
    borderColor: Color = PrimaryColor,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.wrapContentSize()) {
        Box(modifier = Modifier.wrapContentSize()) {
            Box(
                modifier = Modifier
                    .offset(0.dp, 0.dp)
                    .size(width, height)
                    .border(borderSize, borderColor, RoundedCornerShape(cornerRadius))
            )

            // Tô màu trắng cho các phần cạnh của hình vuông
            Box(
                modifier = Modifier
                    .offset(cornerKeepLength / 2, 0.dp - borderSize / 2)
                    .size(width - cornerKeepLength, height + borderSize)
                    .background(Color.White, RectangleShape)
            )

            Box(
                modifier = Modifier
                    .offset(0.dp - borderSize / 2, cornerKeepLength / 2)
                    .size(width + borderSize, height - cornerKeepLength)
                    .background(Color.White, RectangleShape)
            )

            // Phần tô màu trắng bên phải
            Box(
                modifier = Modifier
                    .offset(width - cornerKeepLength / 2 - borderSize / 2, cornerKeepLength / 2)
                    .size(cornerKeepLength / 2 + borderSize / 2, height - cornerKeepLength)
                    .background(Color.White, RectangleShape)
            )

            content()
        }
    }
}


@Preview
@Composable
fun QRScreenPreview(){
    MainBackgroundScreen(title = "Quét mã QR") {
        ScanQRContent()
    }
}