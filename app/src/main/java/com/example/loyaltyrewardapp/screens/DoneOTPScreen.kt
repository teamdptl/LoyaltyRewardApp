package com.example.loyaltyrewardapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.navigation.Screens

class DoneOTPScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                doneOTPScreen()
            }
        }
    }
}

@Composable
fun doneOTPScreen(navController: NavController = rememberNavController()){
    Column(modifier = Modifier.fillMaxSize()) {
        titleDoneOTP()
        btnBackHome(navController)
    }
}

@Composable
fun titleDoneOTP(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 20.dp)
    ){
        Image(painter = painterResource(id = R.drawable.otp_done), contentDescription = null, modifier = Modifier
            .size(width = 361.dp, height = 253.dp)
            .padding(top = 30.dp))
        Spacer(modifier = Modifier.padding(all = 300.dp))
        Text(
            text = "Thành công",
            style = TextStyle(fontSize = 32.sp),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 10.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Tài khoản đã đăng kí thành công",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 70.dp),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}
@Composable
fun btnBackHome(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TextButton(
            onClick = {
                navController.navigate(Screens.LoginScreen.name)
                /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp)
                .clip(RoundedCornerShape(100.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF37A1ED)),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
        ) {
            Text(
                text = "Đi tới đăng nhập",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
            )
        }
    }
}

@Preview(showBackground = true )
@Composable
fun PreviewDoneOTP(){
    doneOTPScreen()
}