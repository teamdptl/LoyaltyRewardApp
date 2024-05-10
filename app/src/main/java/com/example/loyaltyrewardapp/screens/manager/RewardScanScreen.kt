package com.example.loyaltyrewardapp.screens.manager

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.viewmodel.ManagerScanViewModel

@Composable
fun RewardScanScreen(navController: NavHostController, userId: String?, rewardId: String?, viewModel: ManagerScanViewModel = ManagerScanViewModel()) {
    val successMess by remember {
        viewModel.successRewardMessage
    }
    val errorMess by remember {
        viewModel.errorRewardMessage
    }

    LaunchedEffect(null) {
        if (userId != null && rewardId != null) {
            viewModel.receiveReward(userId, rewardId)
        }
    }

    MainBackgroundScreen(navController = navController, title = "Đổi quà") {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
        ){
            if (successMess != null){
                Image(painter = painterResource(id = R.drawable.otp_done), contentDescription = null, modifier = Modifier
                    .size(width = 361.dp, height = 253.dp)
                    .padding(top = 30.dp))
                Spacer(modifier = Modifier.padding(all = 300.dp))
                Text(
                    text = "Thành công",
                    style = TextStyle(fontSize = 22.sp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = successMess?.message?:"Đổi quà thành công",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 70.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                )
            }

            if (errorMess != null){
                Image(painter = painterResource(id = R.drawable.error), contentDescription = null, modifier = Modifier
                    .size(width = 361.dp, height = 253.dp)
                    .padding(top = 30.dp))
                Spacer(modifier = Modifier.padding(all = 300.dp))
                Text(
                    text = "Thất bại",
                    style = TextStyle(fontSize = 22.sp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = errorMess?.message?:"Thất bại đổi quà",
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
    }
}