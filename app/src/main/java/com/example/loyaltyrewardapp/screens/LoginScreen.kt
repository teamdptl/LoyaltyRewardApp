package com.example.loyaltyrewardapp.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.components.MainHeader

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        findViewById<View>(R.id.button).setOnClickListener {
//            val intent = Intent(this@LoginScreen, MainScreen::class.java)
//            startActivity(intent)
//        }
        setContent {
            MainHeader()
        }
    }
}
