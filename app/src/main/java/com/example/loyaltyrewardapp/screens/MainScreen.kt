package com.example.loyaltyrewardapp.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.BottomNavigation
import com.example.loyaltyrewardapp.navigation.AppNavigation
import com.google.firebase.auth.FirebaseAuth

class MainScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword("duy@gmail.com", "duy@gmail.com")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("User", "signInWithEmail:success")
                    val user = auth.currentUser;
                    user?.getIdToken(false)?.addOnCompleteListener(this){
                        if(it.isSuccessful){
                            val idToken = it.result?.token
                            Log.d("User", "idToken: $idToken")
                        }
                    }

                    user?.getIdToken(false)?.addOnCompleteListener(this){
                        if(it.isSuccessful){
                            val idToken = it.result?.token
                            Log.d("User", "idToken: $idToken")
                        }
                    }
                } else {
                    Log.d("User", "signInWithEmail:fail")
                }
            }
        setContent {
            BottomNavigation {
                AppNavigation()
            }
        }
    }
}