package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loyaltyrewardapp.components.HistoryItem
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.data.viewmodel.HistoryViewModel


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
fun HistoryPreview(viewModel:HistoryViewModel = HistoryViewModel()){
    val listHistory by remember { viewModel.histories }
    
    LaunchedEffect(null) {
        viewModel.getHistoryPoint()
        Log.d("Loading", "Dang load du lieu lịch sử")
    }
    
    MainBackgroundScreen("Lịch sử điểm"){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = listHistory,
                itemContent = {
                    HistoryItem(
                        item = it,
                    )
                }
            )
        }
    }
}


