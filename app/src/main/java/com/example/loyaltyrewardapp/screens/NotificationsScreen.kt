package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.CardList
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.components.SquareImage
import com.example.loyaltyrewardapp.data.HistoryProvider
import com.example.loyaltyrewardapp.data.model.HistoryModel

class NotificationsScreen : ComponentActivity(){
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
fun NotificationsItem(history: HistoryModel){
    CardList(){
        Row(Modifier.clickable {  }) {
            SquareImage(
                item = history,
                pictureUrlProperty = HistoryModel::pictureUrl
            )
            Column( // Left half for history details
                modifier = Modifier
                    .padding(8.dp)
//                        .fillMaxWidth(0.8f) // Occupy 50% of width
                    .weight(1f), // Ensure equal weight with Text
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                ) {
                    Text(text = history.title, fontWeight = FontWeight.SemiBold,maxLines = 1,modifier = Modifier.fillMaxWidth(0.5f)
                    )
//                    Spacer(modifier = Modifier.weight(1f)) // Spacer for even spacing
                    Text(text = history.time, maxLines = 1,  textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = history.des, maxLines = 1,)

            }


        }
    }
}

@Preview
@Composable
fun NotificationsPreview(){
    val histories = remember { HistoryProvider.historyList }

    MainBackgroundScreen("Thông báo"){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = histories,
                itemContent = {
                    NotificationsItem(history = it)
                }
            )
        }
    }
}