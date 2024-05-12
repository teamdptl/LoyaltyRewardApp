package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.loyaltyrewardapp.data.api.ApiSingleton
import com.example.loyaltyrewardapp.navigation.GuestNavigation
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiSingleton.initialize(applicationContext)

        auth = FirebaseAuth.getInstance()

//        logout
//        auth.signOut()

        auth.signInWithEmailAndPassword("+84890516934@app.vn", "1234567")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("User", "signInWithEmail:success")
                    val user = auth.currentUser;
                    user?.getIdToken(false)?.addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val idToken = it.result?.token
                            Log.d("User", "idToken: $idToken")
                        }
                    }
                } else {
                    Log.d("User", "signInWithEmail:fail")
                }
            }
        setContent {
            MaterialTheme {
                GuestNavigation()
//                LoginScreen()
//                val viewModel = AdminCURCouponViewModel()
//                CURCouponScreen(couponId = "663a4e93d3b422b0fe0e235b", screen = "R", couponViewModel = viewModel)

//                val viewModel = AdminCURShopViewModel()
//                CURShopScreen(shopId = "663a4e93d3b422b0fe0e2356", screen = "C", shopViewModel = viewModel)
//                val viewModel = AdminCURServiceViewModel()
//                CURServiceScreen(serviceId = "663a4e93d3b422b0fe0e2357", screen = "C", serviceViewModel = viewModel)
            }
        }


    }
}

