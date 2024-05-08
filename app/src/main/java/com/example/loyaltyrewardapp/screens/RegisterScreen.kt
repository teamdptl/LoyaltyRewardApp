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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loyaltyrewardapp.R
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.navigation.Screens
import com.example.loyaltyrewardapp.ui.ScreenState


@Composable
fun registerScreen(navController: NavHostController = rememberNavController()) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Title()
        ChangeUserRegister()
        getField(navController)
    }
}

@Composable
fun Title() {
    Box(
        modifier = Modifier
            .width(307.dp)
            .height(96.dp)
            .padding(top = 30.dp)
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
            .height(height = 85.dp)
            .padding(top = 30.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Gray.copy(alpha = 0.1f)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextButton(
            onClick = { selectedButton.value = true },
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
            colors = if (!selectedButton.value) ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ) else ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Chủ cửa hàng", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun getField(navController: NavHostController) {
    Column(
        modifier = Modifier
            .width(327.dp)
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        val userName = remember {
            mutableStateOf("")
        }
        val numberPhone = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val isPasswordVisible = remember {
            mutableStateOf(false)
        }
        val isTypingPhoneNumber = remember { mutableStateOf(false) }
        val isTypingPassword = remember { mutableStateOf(false) }
        val isTypingName = remember {
            mutableStateOf(false)
        }
        val isNameValid = userName.value.isNotEmpty() && userName.value.length >= 5
        val isPhoneNumberValid =
            numberPhone.value.isNotEmpty() && isValidPhoneNumber(numberPhone.value)
        val isPasswordValid = password.value.isNotEmpty() && password.value.length >= 6
        val isLoginEnabled = isPhoneNumberValid && isPasswordValid && isNameValid
        val focusManager = LocalFocusManager.current
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Họ tên",
            style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            color = Color.Black.copy(alpha = 0.5f)
        )
        OutlinedTextField(
            value = userName.value,
            onValueChange = {
                userName.value = it
                userName.value.length < 5
                isTypingName.value = true
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ), keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            colors = if (isTypingName.value && userName.value.isNotEmpty() && userName.value.length >= 5) {
                TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Green)
            } else {
                TextFieldDefaults.outlinedTextFieldColors()
            },textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                , shape = RoundedCornerShape(10.dp)

        )

        Text(
            text = "Số điện thoại",
            style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = 20.dp)
        )
        OutlinedTextField(
            value = numberPhone.value,
            onValueChange = { newNumberPhone ->
                if (newNumberPhone.length <= 10 && newNumberPhone.all { it.isDigit() })
                    numberPhone.value = newNumberPhone
                isTypingPhoneNumber.value = true
            },
            colors = if (isTypingPhoneNumber.value && isValidPhoneNumber(numberPhone.value)) {
                TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Green)
            } else {
                TextFieldDefaults.outlinedTextFieldColors()
            },
            isError = isTypingPhoneNumber.value && numberPhone.value.isNotEmpty() && !isValidPhoneNumber(
                numberPhone.value
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            )
            , keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Next)})
            ,textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                , shape = RoundedCornerShape(10.dp)

        )

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
                password.value.length >= 6
                isTypingPassword.value = true
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            isError = isTypingPassword.value && password.value.isNotEmpty() && password.value.length < 6,
            colors = if (isTypingPassword.value && password.value.length >= 6) {
                TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Green)
            } else {
                TextFieldDefaults.outlinedTextFieldColors()
            },
            visualTransformation = if (isPasswordVisible.value)
                VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    }
                ) {
                    val icon =
                        if (isPasswordVisible.value) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    Icon(icon, contentDescription = "Hidden/Show password")
                }
            },textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp), shape = RoundedCornerShape(10.dp)
        )
        Button(
            onClick = {
//                sendOTP(numberPhone.value)
                      /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF37A1ED)),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
            enabled = isLoginEnabled
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
                text = "Đăng nhập", fontWeight = FontWeight.Bold, modifier = Modifier
                    .clickable(onClick = {
                        navController.navigate(Screens.LoginScreen.name)
                    })
                    .padding(start = 3.dp), color = Color(0xFF37A1ED)
            )

        }

    }
}


fun isValidPhoneNumber(phoneNumber: String): Boolean {
    if (phoneNumber.isBlank()) return false
    val prefixList = listOf("03", "05", "07", "08", "09")
    return phoneNumber.length == 10 && prefixList.any { phoneNumber.startsWith(it) }
}

//@OptIn(ExperimentalTime::class)
//fun sendOTP( phoneNumber: String) {
//    val auth = FirebaseAuth.getInstance()
//    val options = PhoneAuthOptions.newBuilder(auth)
//        .setPhoneNumber(phoneNumber) // Phone number to verify
//        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//        .setActivity(this) // Activity (for callback binding)
//        .setCallbacks( object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                // This callback will be invoked in two situations:
//                // 1 - Instant verification. In some cases the phone number can be instantly
//                //     verified without needing to send or enter a verification code.
//                // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                //     detect the incoming verification SMS and perform verification without
//                //     user action.
//                signInWithPhoneAuthCredential(credential)
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                // This callback is invoked in an invalid request for verification is made,
//                // for instance if the the phone number format is not valid.
//
//                if (e is FirebaseAuthInvalidCredentialsException) {
//                    // Invalid request
//                } else if (e is FirebaseTooManyRequestsException) {
//                    // The SMS quota for the project has been exceeded
//                } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
//                    // reCAPTCHA verification attempted with null Activity
//                }
//
//                // Show a message and update the UI
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken,
//            ) {
//                // The SMS verification code has been sent to the provided phone number, we
//                // now need to ask the user to enter the code and then construct a credential
//                // by combining the code with a verification ID.
//
//                // Save verification ID and resending token so we can use them later
//                var storedVerificationId = verificationId
//                var resendToken = token
//            }
//        }) // OnVerificationStateChangedCallbacks
//        .build()
//    PhoneAuthProvider.verifyPhoneNumber(options)
//
//
//}
//
// fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//     val auth = FirebaseAuth.getInstance()
//     auth.signInWithCredential(credential)
//        .addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//
//                val user = task.result?.user
//            } else {
//                // Sign in failed, display a message and update the UI
//                if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                    // The verification code entered was invalid
//                }
//                // Update UI
//            }
//        }
//}


@Preview(showBackground = true)
@Composable
fun RegisterDefaultView() {
    registerScreen()
}
