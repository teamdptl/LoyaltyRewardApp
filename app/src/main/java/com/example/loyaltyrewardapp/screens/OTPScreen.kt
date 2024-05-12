package com.example.loyaltyrewardapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.data.DataUser
import com.example.loyaltyrewardapp.data.viewmodel.RegisterViewModel
import com.example.loyaltyrewardapp.navigation.Screens
import com.example.loyaltyrewardapp.ui.getOTP.otp

class OTPScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                OTPScreens()

            }
        }
    }
}

@Composable
fun OTPScreens(navController: NavController= rememberNavController(), viewModel: RegisterViewModel = RegisterViewModel()) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        btnIconBackScreen(navController)
        TitleOTP()
        InputOTP(length = 6, onOtpEntered = { otp -> Log.d("OTP value", "val: $otp") })
//        BackSendCodeOTP()
        BtnConfirm(navController,viewModel,context)
        Log.d("data", "data: "+DataUser._name+","+DataUser.phone+","+DataUser.password+","+DataUser.role)
    }
}

@Composable
fun btnIconBackScreen(navController: NavController) {
    IconButton(
        onClick = {
                  navController.navigate(Screens.registerScreen.name)
                  /*TODO*/ },
        modifier = Modifier
            .width(20.dp)
            .height(20.dp)
            .offset(x = 20.dp, y = 55.dp)
    ) {
        androidx.compose.material.Icon(
            imageVector = Icons.Rounded.ArrowBackIosNew,
            contentDescription = ""
        )
    }
}

@Composable
fun TitleOTP() {
    Box(
        modifier = Modifier
            .width(335.dp)
            .height(97.dp)
            .offset(x = 28.dp, y = 85.dp)
    ) {
        Text(
            text = "Xác thực OTP",
            style = TextStyle(fontSize = 32.sp),
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Đã gửi mã xác thực. Vui lòng kiểm tra tin nhắn.",
            modifier = Modifier.align(Alignment.BottomStart),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}

object IsButtonVisible {
    val isButtonVisible: MutableState<Boolean> = mutableStateOf(false)

}
object getOTP {
    var otp by mutableStateOf(mutableListOf("","","","","",""))

    val getotp: String
        get() = otp.joinToString(separator = "")
}

@Composable
fun InputOTP(
    length: Int,
    onOtpEntered: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val focusRequesters = remember { List(length) { FocusRequester() } }
    var isOutlineTextField = remember { List(length) { mutableStateOf(true) } }
    var startFocusLast = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 120.dp)
            .fillMaxWidth()
            .clickable {
                if (otp.all { it.isNotBlank() } && otp.all { it.isDigitsOnly() && !startFocusLast.value }) {
                    focusRequesters[5].requestFocus()
                    startFocusLast.value = true
                }
            },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                ,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            var stopRecieveKeyEvent = false
            for (i in 0 until length) {
                OtpDigitInput(
                    value = otp[i],
                    focusRequester = focusRequesters[i],
                    modifier = Modifier
                        .onKeyEvent { event ->
                            Log.d("In key event", otp[i])
                            if (event.key == Key.Backspace && i > 0 && !stopRecieveKeyEvent) {
                                Log.d("In key event", otp[i])
                                otp = otp
                                    .toMutableList()
                                    .also {
                                        it[i - 1] = ""
                                    }
                                focusRequesters[i - 1].requestFocus()
                                stopRecieveKeyEvent = true
                                return@onKeyEvent true;
                            }
                            false
                        }

                    ,
                    isOutlineTextField = isOutlineTextField[i],
                    onValueChange = { newValue ->
                        otp = otp.toMutableList().also {
                            it[i] = newValue.takeLast(1)
                        }
                        Log.d("Tuan", "InputOTP: "+ newValue+" " + i+" " + otp[i])

                        if (newValue.length == 1 && i < length-1 ) {
                            focusRequesters[i + 1].requestFocus()
                        }

                        if(newValue.isEmpty() ){
                            stopRecieveKeyEvent = true
                        }
                        if (otp.all { it.isNotBlank() } && otp.all { it.isDigitsOnly() }) {
                            onOtpEntered(otp.joinToString(separator = ""))
                            focusManager.clearFocus()
                            startFocusLast.value = false
                        }
                        if (newValue.isNotEmpty() ){
                            IsButtonVisible.isButtonVisible.value = true

                        }else{
                            IsButtonVisible.isButtonVisible.value = false
                        }
              }
                )
            }
        }
    }
    LaunchedEffect(otp) {
        val nextEmptyIndex = otp.indexOfFirst { it.isBlank() }
        if (nextEmptyIndex != -1) {
            focusRequesters[nextEmptyIndex].requestFocus()
        }
    }
}

@Composable
private fun OtpDigitInput(
    value: String,
    focusRequester: FocusRequester,
    modifier: Modifier,
    isOutlineTextField: MutableState<Boolean>,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center, fontSize = 18.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF37A1ED),
            unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
            backgroundColor = Color(0xFFD9D9D9)
        ),
        enabled = isOutlineTextField.value,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(4.dp)
            .width(50.dp)
            .height(50.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .focusRequester(focusRequester),
    )
}


//@Composable
//fun BackSendCodeOTP() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 30.dp),
//        Arrangement.Center
//    ) {
//        Text(text = "Chưa nhận được mã?", modifier = Modifier.alpha(0.5f))
//        Text(
//            text = " Gửi lại", fontWeight = FontWeight.Bold, modifier = Modifier
//                .clickable(onClick = {
//                    Unit
//                })
//                .padding(start = 3.dp), color = Color(0xFF37A1ED)
//        )
//
//    }
//}

@Composable
fun BtnConfirm(navController: NavController, viewModel: RegisterViewModel, context: Context) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = {

                viewModel.createResgister(DataUser._name,DataUser.phone,DataUser.password,DataUser.role,
                    getOTP.getotp,context,navController)
//                isCreateAccount.isCheckOTP = isCreateAccount.isCheckOTP
                Log.d("data", "data: "+DataUser._name+","+DataUser.phone+","+DataUser.password+","+DataUser.role+","+ getOTP.getotp)
//                if (isCreateAccount.isCheckOTP){
//                    navController.navigate(Screens.doneOTPScreen.name)
//                }
                      }
                ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, bottom = 150.dp)
                .clip(RoundedCornerShape(100.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF37A1ED)),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
            enabled = IsButtonVisible.isButtonVisible.value
        ) {
            Text(
                text = "Xác nhận",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
            )
        }
    }
}
