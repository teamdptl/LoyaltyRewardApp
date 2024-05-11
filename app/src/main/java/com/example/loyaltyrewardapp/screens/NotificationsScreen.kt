package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.components.NotificationsItem
import com.example.loyaltyrewardapp.data.HistoryProvider
import com.example.loyaltyrewardapp.data.viewmodel.HistoryViewModel

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


@Preview
@Composable
fun NotificationsPreview(viewModel: HistoryViewModel = HistoryViewModel()){
    val histories by remember { viewModel.histories }

    MainBackgroundScreen("Thông báo"){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = histories,
                itemContent = {
//                    NotificationsItem(history = it)
                    NotificationsItem(
                        item = it,
//                        titleProvider = { it.title },
//                        timeProvider = { it.time },
//                        descriptionProvider = { it.des },
//                        pictureUrlProvider = { it.pictureUrl }
                    )
                }
            )
        }
    }
}