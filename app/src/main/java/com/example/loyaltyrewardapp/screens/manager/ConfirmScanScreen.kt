package com.example.loyaltyrewardapp.screens.manager

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.SharedObject
import com.example.loyaltyrewardapp.data.model.DetailShopService
import com.example.loyaltyrewardapp.data.viewmodel.ManagerConfirmScanViewModel
import com.example.loyaltyrewardapp.data.viewmodel.ManagerScanViewModel
import com.example.loyaltyrewardapp.data.viewmodel.ShopDetailViewModel
import com.example.loyaltyrewardapp.ui.theme.TextBlackColor

@Composable
fun ConfirmScanScreen(navController: NavHostController = rememberNavController(), userId: String, viewModel: ManagerConfirmScanViewModel = ManagerConfirmScanViewModel(), shopViewModel: ShopDetailViewModel = ShopDetailViewModel(), shopScan: ManagerScanViewModel = ManagerScanViewModel()) {
    val user by remember {
        viewModel.user
    }

    val shop by remember {
        shopViewModel.shop
    }

    var selectService: DetailShopService? by remember {
        mutableStateOf(null)
    }

    val accumulateMessage by shopScan.accumulateResponse.observeAsState()
    
    val accumulateError by shopScan.errorAccumulateMessage.observeAsState()

    LaunchedEffect(null) {
        viewModel.fetchUser(userId)
        shopViewModel.getShopDetail(SharedObject.shopId)
    }

    Log.d("TAG", "ConfirmScanScreen: $accumulateMessage")

    if (accumulateMessage != null) {
        Toast.makeText(LocalContext.current, accumulateMessage?.message, Toast.LENGTH_LONG).show()
        Log.d("TAG", "ConfirmScanScreen: ${accumulateMessage?.message}")
    }

    if (accumulateError != null) {
        Toast.makeText(LocalContext.current, accumulateError?.message, Toast.LENGTH_LONG).show()
        Log.d("TAG", "ConfirmScanScreen: ${accumulateMessage?.message}")
    }

    MainBackgroundScreen(navController = navController, title = "Chọn dịch vụ") {
        Column(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)
        ){
            Text(text = "Thông tin khách hàng", fontSize = 16.sp, color = TextBlackColor, fontWeight = FontWeight.W500,
                modifier = Modifier.padding(bottom = 16.dp))
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)){
                Row(modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Tên khách hàng:", fontSize = 16.sp, color = TextBlackColor)
                    Text(text = user?.name?:"", fontSize = 16.sp, color = TextBlackColor)
                }
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Số điện thoại:", fontSize = 16.sp, color = TextBlackColor)
                    Text(text = user?.phone?:"", fontSize = 16.sp, color = TextBlackColor)
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 16.dp, start = 10.dp, end = 10.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Text(text = "Chọn dịch vụ khách đã dùng", fontSize = 16.sp, color = TextBlackColor, textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 8.dp, start = 10.dp)
                    .fillMaxWidth())
            MyDropdownMenu(shop?.services ?: emptyList(), onItemSelected = {
                selectService = it
            })

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)){
                Button(onClick = {
                    shopScan.accumulatePoint(userId, selectService?._id ?:"")
                }, content = {
                    Text(text = "Xác nhận")
                }, enabled = selectService != null,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp))
            }
        }
    }

}

@Composable
fun MyDropdownMenu(listService: List<DetailShopService>, onItemSelected: (item: DetailShopService) -> Unit){

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                }
            ,
            label = {Text("Dịch vụ")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            listService.forEach { it ->
                DropdownMenuItem(onClick = {
                    mSelectedText = it.name + " (+ ${it.points_reward} điểm)"
                    mExpanded = false
                    onItemSelected(it)
                }, text = {
                    Text(text = it.name + " (+ ${it.points_reward} điểm)")
                })
            }
        }
    }
}
