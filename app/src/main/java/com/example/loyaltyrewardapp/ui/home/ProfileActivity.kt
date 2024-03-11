package com.example.loyaltyrewardapp.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.service.autofill.DateTransformation
import android.view.autofill.AutofillId
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.R
import java.util.Calendar
import java.util.Date

class ProfileActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {

            }
        }
    }
}
@Composable
fun MainContainer(){
    Card(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
    ) {
        Column(Modifier.padding(40.dp, 20.dp)) {
            CircleAvatar()
            Spacer(modifier = Modifier.size(20.dp))
            InfoBox()
        }
    }
}

@Composable
fun CircleAvatar() {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box {
            Image(
                painterResource(id = R.drawable.avatar), contentDescription = "Your avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
            )
            IconButton(
                onClick = { /*TODO*/ },
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

@Composable
fun InfoBox(){
    Column {
        fieldInfo(title = "Họ tên", fieldValue = "Võ Minh Tuấn")
        Spacer(modifier = Modifier.size(10.dp))
        fieldInfo(title = "Số điện thoại", fieldValue = "0819107257")
        Spacer(modifier = Modifier.size(10.dp))
        fieldInfo(title = "Ngày sinh", fieldValue = "11-08-2002", type = "date")
        Spacer(modifier = Modifier.size(10.dp))
        fieldInfo(title = "Email", fieldValue = "minhtuan.1108tn@gmail.com")
    }
}

@Composable
fun fieldInfo(title : String, fieldValue : String, type: String = "text"){
    Column {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.Blue
        )
        if(type.equals("text", true)){
            TextField(value = fieldValue,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color(0.933f, 0.925f, 0.925f, 1.0f)
                ))
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
            val mDate = remember { mutableStateOf("") }

            val mDatePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
                }, mYear, mMonth, mDay
            )

            TextField(
                value = mDate.value,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { mDatePickerDialog.show() },
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color(0.933f, 0.925f, 0.925f, 1.0f)
                ),
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
fun DefaultPreview(){
    MainContainer()
}


