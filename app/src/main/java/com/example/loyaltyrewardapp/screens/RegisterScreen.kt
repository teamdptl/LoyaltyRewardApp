package com.example.loyaltyrewardapp.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R

class RegisterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {

            }
        }
    }
}

@Composable
fun registerScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Tilte()
        ChangeUserRegister()
        getField()
    }
}

@Composable
fun Tilte() {
    Box(
        modifier = Modifier
            .width(307.dp)
            .height(66.dp)
            .offset(x = 28.dp, y = 85.dp)
    ) {

        Text(
            text = "Đăng ký",
            style = TextStyle(fontSize = 32.sp),
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Tạo tài khoản mới để sử dụng dịch vụ",
            modifier = Modifier.align(Alignment.BottomStart),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}
@Composable
fun ChangeUserRegister() {
    val selectedButton = remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .width(width = 327.dp)
            .height(height = 60.dp)
            .offset(x = 28.dp, y = 107.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Gray.copy(alpha = 0.1f)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextButton(
            onClick = { selectedButton.value = true},
            modifier = Modifier
                .width(width = 151.dp)
                .height(height = 48.dp)
                .padding(start = 5.dp)
                .align(Alignment.CenterVertically),
            colors = if (selectedButton.value) ButtonDefaults.buttonColors(backgroundColor = Color.White) else ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)

        ) {
            Text(text = "Khách hàng", color = Color.Black, fontWeight = FontWeight.Bold)
        }
        TextButton(
            onClick = { selectedButton.value = false }, // Khi click vào button "Chủ cửa hàng"
            modifier = Modifier
                .width(width = 152.dp)
                .height(height = 48.dp)
                .padding(end = 5.dp)
                .align(Alignment.CenterVertically),
            colors = if (!selectedButton.value)  ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )  else ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Chủ cửa hàng", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun getField() {
    Column(
        modifier = Modifier
            .width(327.dp)
            .fillMaxHeight()
            .offset(x = 27.dp, y = 121.dp)
    ) {
        var userName by remember {
            mutableStateOf("")
        }
        Text(
            text = "Họ tên",
            style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            color = Color.Black.copy(alpha = 0.5f)
        )
        OutlinedTextField(
            label = {Text( text = "Họ tên", color = Color.Black.copy(alpha = 0.2f))},
            value = userName,
            onValueChange = { userName = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(Color.Transparent), shape = RoundedCornerShape(10.dp)

        )
        var numberPhone by remember {
            mutableStateOf("")
        }
        Text(
            text = "Số điện thoại",
            style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 20.dp)
        )
        OutlinedTextField(
            label = { Text(text = "Số điện thoại", color = Color.Black.copy(alpha = 0.2f)) },
            value = numberPhone,
            onValueChange = { numberPhone = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(Color.Transparent), shape = RoundedCornerShape(10.dp)

        )
        val password = remember {
            mutableStateOf("")
        }
        val isPasswordVisible = remember {
            mutableStateOf(false)
        }
        Text(
            text = "Mật khẩu",
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(fontSize = 16.sp),
            color = Color.Black
                .copy(alpha = 0.5f)
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                } ,
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Password) ,
            label = { Text(text="Mật khẩu", color = Color.Black.copy(alpha = 0.2f)) },
            visualTransformation = if (isPasswordVisible.value)
                VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    }
                ) {
                    val icon = if (isPasswordVisible.value)Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    Icon(icon, contentDescription = "Hidden/Show password")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp), shape = RoundedCornerShape(10.dp)
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
                text = "Đăng ký",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .alpha(0.1f)
                    .background(color = Color.Black)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Hoặc đăng nhập",
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .alpha(0.1f)
                    .background(color = Color.Black)
                    .align(Alignment.CenterVertically)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.google.com")
                    /*TODO*/
                },
                modifier = Modifier
                    .alpha(0.9f)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Icon Google",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(size = 18.dp)
                )
                Text(
                    text = "Google", fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 5.dp, top = 10.dp, end = 30.dp, bottom = 10.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 14.sp
                )
            }
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.facebook.com")/*TODO*/
                },
                modifier = Modifier
                    .alpha(0.9f)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5))
            ) {
                Icon(
                    (Icons.Filled.Facebook),
                    contentDescription = "",
                    tint = Color.Blue,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "Facebook", fontWeight = FontWeight.Bold, modifier = Modifier
                        .padding(start = 5.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                        .align(Alignment.CenterVertically), fontSize = 14.sp
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            Arrangement.Center
        ) {
            Text(text = "Đã có tài khoản ?", modifier = Modifier.alpha(0.5f))
            Text(
                text = "Đăng nhập", fontWeight = FontWeight.Bold ,modifier = Modifier
                    .clickable(onClick = {
                        Unit
                    })
                    .padding(start = 3.dp), color = Color(0xFF37A1ED)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultView() {
    registerScreen()
}
