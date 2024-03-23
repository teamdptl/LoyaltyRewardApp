package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.BottomNavigation
import com.example.loyaltyrewardapp.navigation.AppNavigation

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigation {
                AppNavigation()
            }
        }
    }
}