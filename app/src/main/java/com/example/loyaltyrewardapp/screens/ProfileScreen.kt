package com.example.loyaltyrewardapp.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import com.example.loyaltyrewardapp.navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import java.io.File
import java.util.Calendar
import java.util.Date


@Composable
fun ProfileContent(navController: NavController = rememberNavController(), onLogout:() -> Unit = {}, guestNavigation : NavController = rememberNavController(), homeViewModel: UserHomeViewModel = UserHomeViewModel()){
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser = auth.currentUser
//    Log.d("UserId", firebaseUser.toString())
    val context = LocalContext.current



    val user by remember {
        homeViewModel.user
    }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
    }

    var displayName by remember { mutableStateOf(firebaseUser?.displayName.toString() ?: "") }
    val imageUrl by remember { mutableStateOf(firebaseUser?.photoUrl.toString()) }


    fun updateUserProfile(firebaseUser: FirebaseUser,displayName: String?, photoUrl: String?) {
        val profileUpdates = userProfileChangeRequest {
            if (displayName != null) {
                this.displayName = displayName
            }
            if (photoUrl != null) {
                this.photoUri = Uri.parse(photoUrl)
            }
        }

        firebaseUser.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("update xong user firebase", "User profile updated")
                } else {
                    Log.e("update lỗi user firebase", task.exception.toString())
                }
            }
    }

    MainBackgroundScreen("Tài khoản") {
        Column(
            Modifier
                .padding(40.dp, 30.dp)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            if (firebaseUser != null) {
                CircleAvatar(
                    imageUrl,
                    firebaseUser,
                    user,
                    selectedImageUri = selectedImageUri,
                    onImageSelected = { launcher.launch("image/*") }
                )
                Spacer(modifier = Modifier.size(40.dp))


                Column() {
                    fieldInfo(title = "Họ tên", fieldValue = displayName,    onNameChange = { displayName = it }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    fieldInfo(title = "Số điện thoại", fieldValue = firebaseUser.phoneNumber.toString(),  onNameChange = { }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Column {
                        Button(onClick =  {

                            if(selectedImageUri != null){
                                val inputStream = context.contentResolver.openInputStream(selectedImageUri!!)

                                val file = File(context.cacheDir, "fileProfile.png")
                                file.createNewFile()
                                file.outputStream().use { outputStream ->
                                    inputStream?.copyTo(outputStream)
                                }
                                val urlCloud = homeViewModel.uploadImage(file)
                                Log.d("urlCloud", urlCloud.toString())
                                Log.d("displayName nè ba", displayName)
                                updateUserProfile(firebaseUser, displayName, urlCloud.toString())

                            }else{
                                Log.d("k đổi ảnh", "huhu")

                                updateUserProfile(firebaseUser, displayName, null)
                                Log.d("displayName nè ba", displayName)

                            }
                        } ,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0E4AFF),
                                contentColor = Color.White,
                                disabledContainerColor = Color(0xB00E4AFF)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Lưu", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                        Button(onClick = {
                            auth.signOut()
                            onLogout()
                            guestNavigation.navigate(Screens.LoginScreen.name)
                                         },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF0E23),
                                contentColor = Color.White,
                                disabledContainerColor = Color(0xB0FF0E23)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Đăng xuất", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircleAvatar(imageUrl: String,firebaseData: FirebaseUser, user: User,   selectedImageUri: Uri?,
                 onImageSelected: () -> Unit) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box {
            val imageModifier = Modifier
                .size(100.dp)
                .clip(shape = CircleShape)
            if (selectedImageUri != null) {
                // Display selected image if available
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Your avatar",
                    modifier = imageModifier
                )
            } else {
                // Display default avatar
                Image(
                    painterResource(id = R.drawable.avatar),
                    contentDescription = "Your avatar",
                    modifier = imageModifier
                )
            }
            IconButton(
                onClick = onImageSelected,
                modifier = Modifier
                    .offset(55.dp, 55.dp)
                    .padding(5.dp)
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "",
                    tint = Color(android.graphics.Color.CYAN),
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(5.dp))
                        .border(BorderStroke(2.dp, Color.Cyan), RoundedCornerShape(5.dp))
                        .padding(3.dp)
                        .size(16.dp)
                )
            }
        }
    }
}


//@Composable
//fun InfoBox(displayName: String,firebaseData: FirebaseUser, onSaveClicked: () -> Unit){
//
//
//    Column() {
//        fieldInfo(title = "Họ tên", fieldValue = displayName,    onNameChange = { displayName = it }
//        )
//        Spacer(modifier = Modifier.size(10.dp))
//        fieldInfo(title = "Số điện thoại", fieldValue = firebaseData.phoneNumber.toString(),  onNameChange = { }
//        )
//        Spacer(modifier = Modifier.size(10.dp))
//        Column {
//            Button(onClick =  onSaveClicked ,
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF0E4AFF),
//                    contentColor = Color.White,
//                    disabledContainerColor = Color(0xB00E4AFF)
//                ),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Lưu", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            }
//            Button(onClick = { /*TODO*/ },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFFF0E23),
//                    contentColor = Color.White,
//                    disabledContainerColor = Color(0xB0FF0E23)
//                ),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Đăng xuất", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            }
//        }
//    }
//}

@Composable
fun fieldInfo(title : String, fieldValue : String, onNameChange: (String) -> Unit,type: String = "text"){
    Column {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        if(type.equals("text", true)){
            var textValue by remember{ mutableStateOf(fieldValue) }
            OutlinedTextField(
                value = textValue,
                onValueChange = {
                    textValue = it
                    onNameChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textStyle = TextStyle(
                    fontSize=14.sp,
                    color = Color.Black,
                ),
                shape = RoundedCornerShape(15.dp),
                )
        }

        if(type.equals("date", ignoreCase = true)){
            val mYear: Int
            val mMonth: Int
            val mDay: Int

            // Initializing a Calendar
            val mCalendar = Calendar.getInstance()

            // Fetching current year, month and day
            mYear = mCalendar.get(Calendar.YEAR)
            mMonth = mCalendar.get(Calendar.MONTH)
            mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

            mCalendar.time = Date()
            val mDate = remember { mutableStateOf(fieldValue) }

            val mDatePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    mDate.value = "$mDayOfMonth-${mMonth+1}-$mYear"
                }, mYear, mMonth, mDay
            )

            OutlinedTextField(
                value = mDate.value,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { mDatePickerDialog.show() },
                shape = RoundedCornerShape(15.dp),
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.CalendarMonth,
                            contentDescription = "",
                            Modifier.clickable { mDatePickerDialog.show() }
                        )
                    }
                }
            )


        }
    }
}
@Preview
@Composable
fun ProfilePreview(){
    MainBackgroundScreen("Tài khoản"){
//        ProfileContent()
    }
}


