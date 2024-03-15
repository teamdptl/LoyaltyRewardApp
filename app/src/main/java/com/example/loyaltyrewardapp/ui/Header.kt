package com.example.loyaltyrewardapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.R

@Composable
fun Header(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)){
        Image(painter = painterResource(id = R.drawable.background), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(painterResource(id = R.drawable.user), contentDescription = null, modifier = Modifier.size(40.dp))
                Column (modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = "Duy", color = Color.Black)
                    Text(text = "Khách hàng", color = Color.Gray)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = "0", color = Color.White)
                Icon(imageVector = Icons.Default.Doorbell, contentDescription = null)
            }
        }
    }
}