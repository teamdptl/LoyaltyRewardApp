package com.example.loyaltyrewardapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

/**
 * An composable layout template will help to render an screen like profile, list items and notification list,...
 * Look at example in this link:
 * https://www.figma.com/file/ePgTPBhDsomJAj92Ha9C3D/Android-n%C3%A2ng-cao?type=design&node-id=18-2016&mode=design&t=WB9wX1GIO6hKoYi7-4
 */
@Composable
fun MainBackgroundScreen(title : String, navController: NavController? = rememberNavController(), content: @Composable () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF46BEF8))
    ) {
        /**
         * The header of screen which will show that the title off screen
         */
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                onClick = {
                    navController?.popBackStack()
                },
                colors = IconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .size(35.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(20.dp),
                    tint = Color.Black.copy(alpha = 0.6f)
                )
            }
            Text(text = title,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center))
        }
        /**
         * The main container which will contain your content @Composable layout
         */
        Column {
            Card(modifier = Modifier
                .fillMaxSize(),
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
                content = content
            )
        }
    }
}