package com.example.loyaltyrewardapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Column(Modifier.fillMaxSize()) {
        Tilte()
        getField()
    }
}


@Composable
fun Tilte() {
    Box(
        modifier = Modifier
            .width(226.dp)
            .height(66.dp)
            .offset(x = 28.dp, y = 85.dp)
    ) {

        Text(
            text = "Đăng nhập",
            style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Hệ thống tích điểm tiện ích",
            modifier = Modifier.align(Alignment.BottomStart),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun getField() {
    Column(
        modifier = Modifier
            .width(327.dp)
            .height(300.dp)
            .offset(x = 27.dp, y = 171.dp)
    ) {
        var numberPhone by remember {
            mutableStateOf("")
        }
        Text(
            text = "Số điện thoại",
            style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            color = Color.Black.copy(alpha = 0.5f)
        )
        TextField(
            label = { Text("123456") },
            value = numberPhone,
            onValueChange = { numberPhone = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp))
        )
        var password by remember {
            mutableStateOf("")
        }
        Text(
            text = "Mật khẩu",
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 16.sp),
            color = Color.Black
                .copy(alpha = 0.5f)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("*******") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(10.dp)),

            )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF37A1ED)),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                text = "Đăng nhập",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen()
}

