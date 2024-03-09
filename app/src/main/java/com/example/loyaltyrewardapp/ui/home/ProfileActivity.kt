package com.example.loyaltyrewardapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.R

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
    val heightProfile = 450.dp
    Card(modifier = Modifier
        .height(heightProfile)
        .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
    ) {
        Column {
            CircleAvatar()
        }
    }
}

@Composable
fun CircleAvatar(){
    Box {
        Image(painterResource(id = R.drawable.avatar), contentDescription = "Your avatar",
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
            Icon(Icons.Filled.Edit,
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
@Preview
@Composable
fun DefaultPreview(){
    MainContainer()
}


