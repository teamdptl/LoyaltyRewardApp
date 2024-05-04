package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import coil.imageLoader
import com.example.loyaltyrewardapp.navigation.AppNavigation
import com.example.loyaltyrewardapp.ui.OTPPreview
import com.example.loyaltyrewardapp.navigation.GuestNavigation
import com.example.loyaltyrewardapp.screens.manager.CURCouponScreen
import com.example.loyaltyrewardapp.ui.theme.LoyaltyRewardAppTheme
import com.google.firebase.auth.FirebaseAuth

class MainScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoyaltyRewardAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
//                    BottomNavigation {
//                        AppNavigation()
//                    }
//                    OTPPreview()
                }
                // Bật cái này lên khi nào muốn login
//        val isLogin = auth.currentUser != null ;

                val isLogin = true;
                setContent {
                    MaterialTheme {
//                        GuestNavigation(isLogin)
                        CURCouponScreen(
                            couponName = "",
                            description = "",
                            point = 0,
                            timeExpire = 0,
                            imageUri = "https://scontent.fsgn5-8.fna.fbcdn.net/v/t39.30808-6/438302247_1914065639045213_9194821425874351450_n.jpg?stp=dst-jpg_p526x296&_nc_cat=109&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFGT_vgxZipnHfqHKTHruIm-0AAdDVnUMH7QAB0NWdQwTq5VfV1_OFu-eEyl4kj_SN8fwUPxABoOpQlVGPn_Vms&_nc_ohc=fEp6aHTMyJYQ7kNvgFf2QFg&_nc_ht=scontent.fsgn5-8.fna&oh=00_AfCmRbXY4iJ8Ov9TLAQtUPTvD4pb43sZE2mZlSjpmoPAPw&oe=663C34DF",
                            screenState = "R",
                        )
                    }
                }

                auth.signInWithEmailAndPassword("duy@gmail.com", "duy@gmail.com")
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

//                    user?.getIdToken(false)?.addOnCompleteListener(this){
//                        if(it.isSuccessful){
//                            val idToken = it.result?.token
//                            Log.d("User", "idToken: $idToken")
//                        }
//                    }
                        } else {
                            Log.d("User", "signInWithEmail:fail")
                        }
                    }

            }
        }
    }
}