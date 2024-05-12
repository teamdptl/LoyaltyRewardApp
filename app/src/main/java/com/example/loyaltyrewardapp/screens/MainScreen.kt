package com.example.loyaltyrewardapp.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.imageLoader
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURCouponViewModel
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import com.example.loyaltyrewardapp.navigation.GuestNavigation
import com.example.loyaltyrewardapp.screens.manager.CURCouponScreen
import com.example.loyaltyrewardapp.screens.manager.CURShopScreen
import com.example.loyaltyrewardapp.ui.LoginScreen
import com.example.loyaltyrewardapp.ui.OTPScreens
import com.example.loyaltyrewardapp.ui.doneOTPScreen
import com.example.loyaltyrewardapp.ui.theme.LoyaltyRewardAppTheme
import com.google.firebase.auth.FirebaseAuth
import retrofit2.HttpException

class MainScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiSingleton.initialize(applicationContext)

        auth = FirebaseAuth.getInstance()

//        logout
        auth.signOut()

//        auth.signInWithEmailAndPassword("+84890516934@app.vn", "1234567")
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("User", "signInWithEmail:success")
//                    val user = auth.currentUser;
//                    user?.getIdToken(false)?.addOnCompleteListener(this) {
//                        if (it.isSuccessful) {
//                            val idToken = it.result?.token
//                            Log.d("User", "idToken: $idToken")
//                        }
//                    }
//                } else {
//                    Log.d("User", "signInWithEmail:fail")
//                }
//            }
        setContent {
            MaterialTheme {
                GuestNavigation()
//                doneOTPScreen()
//                CURCouponScreen(couponId = "663a4e93d3b422b0fe0e235b", screen = "R")
            }
        }


    }
}