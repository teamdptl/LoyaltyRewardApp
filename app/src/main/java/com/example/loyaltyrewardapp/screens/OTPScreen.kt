package com.example.loyaltyrewardapp.ui

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

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
fun OTPScreens() {
    Column(modifier = Modifier.fillMaxSize()) {
        btnIconBackScreen()
        TitleOTP()
        InputOTP(length = 6, onOtpEntered = { otp -> Unit })
        BackSendCodeOTP()
        BtnConfirm()
    }
}

@Composable
fun btnIconBackScreen() {
    IconButton(
        onClick = { /*TODO*/ },
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
            text = "Đã gửi mã xác thực đến số điện thoại +233 *******53. Vui lòng kiểm tra tin nhắn.",
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

@Composable
fun InputOTP(
    length: Int,
    onOtpEntered: (String) -> Unit
) {
    var otp by remember { mutableStateOf(List(length) { "" }) }
    val focusManager = LocalFocusManager.current
    val focusRequesters = remember { List(length) { FocusRequester() } }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 120.dp)
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (i in 0 until length) {
                OtpDigitInput(
                    value = otp[i],
                    focusRequester = focusRequesters[i],
                    onValueChange = { newValue ->
                        otp = otp.toMutableList().also {
                            it[i] = newValue.takeLast(1)
                        }
                        Log.d("Tuan", "InputOTP: "+ newValue+" " + i+" " + otp[i])

                        if (newValue.length == 1 && i < length-1) {
                            focusRequesters[i + 1].requestFocus()
                        }
                        else if (newValue.isEmpty() && i > 0) {
                            focusRequesters[i - 1].requestFocus()
                        }
                        if (otp.all { it.isNotBlank() } && otp.all { it.isDigitsOnly() }) {
                            onOtpEntered(otp.joinToString(separator = ""))
                            focusManager.clearFocus()
                        }
                        if (newValue.isNotEmpty() && i == 5){
                            IsButtonVisible.isButtonVisible.value = true
                        }else{
                            IsButtonVisible.isButtonVisible.value = false
                        }
                    }
                )
            }
        }
    }


}

@Composable
private fun OtpDigitInput(
    value: String,
    focusRequester: FocusRequester,
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
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .width(50.dp)
            .height(50.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .focusRequester(focusRequester),
    )
}


@Composable
fun BackSendCodeOTP() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        Arrangement.Center
    ) {
        Text(text = "Chưa nhận được mã?", modifier = Modifier.alpha(0.5f))
        Text(
            text = " Gửi lại", fontWeight = FontWeight.Bold, modifier = Modifier
                .clickable(onClick = {
                    Unit
                })
                .padding(start = 3.dp), color = Color(0xFF37A1ED)
        )

    }
}

@Composable
fun BtnConfirm() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = { /*TODO*/ },
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
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OTPPreview() {
    OTPScreens()
}