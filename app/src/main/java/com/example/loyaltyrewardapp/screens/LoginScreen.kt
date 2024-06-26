package com.example.loyaltyrewardapp.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.navigation.Screens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginScreen(navController: NavHostController = rememberNavController(), onLogin: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())) {
        Title()
        getField(navController, onLogin = onLogin)
    }
}
object ScreenState {
    var isToScreen by mutableStateOf(false)
}
@Composable
fun Title() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 20.dp)
                .size(width = 300.dp, height = 200.dp)
        )
        Text(
            text = "Đăng nhập",
            style = TextStyle(fontSize = 32.sp),
            modifier = Modifier.padding(bottom = 10.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Hệ thống tích điểm tiện ích cho cửa hàng",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}
@Composable
fun getField(navController: NavHostController, onLogin: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {


        val isTypingPhoneNumber = remember { mutableStateOf(false) }
        val isTypingPassword = remember { mutableStateOf(false) }

        val numberPhone = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")

        }
        var isPasswordVisible = remember {
            mutableStateOf(false)
        }
        val isPhoneNumberValid =
            numberPhone.value.isNotEmpty() && isValidPhoneNumber(numberPhone.value)
        val isPasswordValid = password.value.isNotEmpty() && password.value.length >= 6
        val isLoginEnabled = isPhoneNumberValid && isPasswordValid
        val focusManager = LocalFocusManager.current
        var isUserLoggedIn by remember { mutableStateOf(false) }
        val context = LocalContext.current

        Text(
            text = "Số điện thoại",
            style = TextStyle(fontSize = 16.sp),
            color = Color.Black.copy(alpha = 0.5f)
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
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
            )
            , keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(FocusDirection.Next)})
             ,textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .background(Color.Transparent), shape = RoundedCornerShape(10.dp)

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
            isError = isTypingPassword.value && password.value.isNotEmpty() && password.value.length < 6,
            colors = if (isTypingPassword.value && password.value.length >= 6) {
                TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Green)
            } else {
                TextFieldDefaults.outlinedTextFieldColors()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
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
                .padding(top = 5.dp),
            shape = RoundedCornerShape(10.dp)
        )
        Button(
            onClick = {
                LoginUsersByEmail(context,navController,numberPhone.value,password.value, onLogin = onLogin)
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
                text = "Đăng nhập",
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
                    intent.data = Uri.parse("https://www.facebook.com")
//                    context.startActivity(intent)/*TODO*/
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
            Text(text = "Chưa có tài khoản ?", modifier = Modifier.alpha(0.5f))
            Text(
                text = "Đăng ký", fontWeight = FontWeight.Bold, modifier = Modifier
                    .clickable(onClick = {
                        navController.navigate(Screens.registerScreen.name)
                    })
                    .padding(start = 3.dp), color = Color(0xFF37A1ED)
            )

        }


    }
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {
    if (phoneNumber.isBlank()) return false
    val prefixList = listOf("0")
    return phoneNumber.length == 10 && prefixList.any { phoneNumber.startsWith(it) }
}
fun LoginUsersByEmail(
    context: Context,
    navController: NavController,
    phoneNumber: String,
    password: String,
    onLogin: () -> Unit = {}
) {
    val auth = FirebaseAuth.getInstance()
    val phoneChangeEmail = "+84" +phoneNumber.substring(1) +"@app.vn"
    println("phone thanh email: $phoneChangeEmail")
    auth.signInWithEmailAndPassword(phoneChangeEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("User", "signInWithEmail:success")
                    val user = auth.currentUser;
                    // TODO: Fix this to navigate to the correct screen manager or user
                    Toast.makeText(context,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()
                    onLogin()
                } else {
                    Log.d("User", "signInWithEmail:fail")
                    Toast.makeText(context,"Số điện thoại hoặc mật khẩu đã sai",Toast.LENGTH_SHORT).show()
                }
            }
}
//Cần điều chỉnh vị hàm này.
//fun toScreenHome(navController: NavController){
////    navController.navigate(Screens.AppNavigationScreen.name)
//    }
//}

@Preview(showBackground = true)
@Composable
fun LoginDefaultPreview() {
    LoginScreen()
}

