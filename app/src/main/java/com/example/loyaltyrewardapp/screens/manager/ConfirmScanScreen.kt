package com.example.loyaltyrewardapp.screens.manager

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ConfirmScanScreen() {
    var expanded by remember { mutableStateOf(false) }

    MainBackgroundScreen(title = "Chọn dịch vụ") {
        Column(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)
        ){
            Text(text = "Thông tin khách hàng", fontSize = 16.sp, color = TextBlackColor, fontWeight = FontWeight.W500,
                modifier = Modifier.padding(bottom = 16.dp))
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)){
                Row(modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Tên khách hàng:", fontSize = 16.sp, color = TextBlackColor)
                    Text(text = "Huỳnh Khánh Duy", fontSize = 16.sp, color = TextBlackColor)
                }
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Số điện thoại:", fontSize = 16.sp, color = TextBlackColor)
                    Text(text = "0987654321", fontSize = 16.sp, color = TextBlackColor)
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 16.dp, start = 10.dp, end = 10.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
            Text(text = "Chọn dịch vụ khách đã dùng", fontSize = 16.sp, color = TextBlackColor, textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp, start = 10.dp).fillMaxWidth())
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                DropdownMenuItem(
                    text = { Text("Load") },
                    onClick = {  }
                )
                DropdownMenuItem(
                    text = { Text("Load") },
                    onClick = {  }
                )
            }
        }
    }
}
