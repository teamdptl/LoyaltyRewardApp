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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.CardList
import com.example.loyaltyrewardapp.components.HistoryItem
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.components.SquareImage
import com.example.loyaltyrewardapp.data.HistoryProvider
import com.example.loyaltyrewardapp.data.model.HistoryModel

class HistoryScreen : ComponentActivity(){
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
fun HistoryContent(){
    Column(Modifier.padding(40.dp, 20.dp)) {
        Spacer(modifier = Modifier.size(20.dp))
//        HistoryListScreen
    }
}


@Preview
@Composable
fun HistoryPreview(){
    val histories = remember { HistoryProvider.historyList }
    MainBackgroundScreen("Lịch sử điểm"){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = histories,
                itemContent = {
                    HistoryItem(
                        item = it,
                        titleProvider = { it.title },
                        timeProvider = { it.time },
                        descriptionProvider = { it.des },
                        pointProvider = { it.point.toString() },
                        pictureUrlProvider = { it.pictureUrl }
                    )
                }
            )
        }
    }
}

