package com.example.loyaltyrewardapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.loyaltyrewardapp.components.MainHeader

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
//        Text(
//            text = "Home Screen"
//        )
        MainHeader()
    }
}