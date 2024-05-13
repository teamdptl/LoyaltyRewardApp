package com.example.loyaltyrewardapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.data.model.NotFoundUserState
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURShopViewModel
import com.example.loyaltyrewardapp.data.viewmodel.FCMViewModel
import com.example.loyaltyrewardapp.data.viewmodel.GuestViewModel
import com.example.loyaltyrewardapp.screens.SplashScreen
import com.example.loyaltyrewardapp.screens.manager.CURShopScreen
import com.example.loyaltyrewardapp.screens.registerScreen
import com.example.loyaltyrewardapp.ui.LoginScreen
import com.example.loyaltyrewardapp.ui.OTPScreens
import com.example.loyaltyrewardapp.ui.doneOTPScreen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

@Composable
fun GuestNavigation(viewModel : GuestViewModel = hiltViewModel(), fcmViewModel: FCMViewModel = hiltViewModel()) {

    val user by viewModel.user.observeAsState()
    var reloadActivity by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf(Screens.SplashScreen.name) }
    val firebaseAuth = FirebaseAuth.getInstance()
    LaunchedEffect(reloadActivity) {
        viewModel.fetchCurrentUser()
    }

    Log.d("TAG", "GuestNavigation: $user")

    if (user == null) {
        startDestination = Screens.SplashScreen.name
    }
    else if (user?.role === NotFoundUserState.role) {
        startDestination = Screens.LoginScreen.name
        Log.d("GuestViewModel", "NotFoundUserState")
    }
    else if (user?.role === UserEmptyState.role) {
        Log.d("GuestViewModel", "UserEmptyState")
        startDestination = Screens.SplashScreen.name
    }
    else if (user?.role == "manager") {
        if (user?.shop == null){
            Log.d("GuestViewModel", ("Error Manager " + user?.shop?.name) ?: "Here")
            startDestination = Screens.CreateShopScreen.name
        }
        else
            startDestination = Screens.ManagerNavigationScreen.name
    }
    else if (user?.role == "user") {
        startDestination = Screens.UserNavigationScreen.name
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("ErrorFCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            fcmViewModel.updateFCMToken(token)
        })
    }

    Log.d("TAG", "Change Screen: $startDestination")

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.SplashScreen.name){
            SplashScreen()
        }
        composable(route = Screens.LoginScreen.name) {
            LoginScreen(navController, onLogin ={
                reloadActivity = true
            })
        }
        composable(route = Screens.registerScreen.name ) {
            registerScreen(navController)
        }
        composable(route = Screens.OTPScreens.name) {
            OTPScreens(navController)
        }
        composable(route = Screens.doneOTPScreen.name) {
            doneOTPScreen(navController)
        }
        composable(route = Screens.UserNavigationScreen.name) {
            UserNavigation(onLogout = {
                reloadActivity = false
            }, guestNavigation = navController)
        }
        composable(route = Screens.ManagerNavigationScreen.name) {
            ManagerNavigation(onLogout = {
                reloadActivity = false
            }, guestNavigation = navController)
        }

        composable(route = Screens.CreateShopScreen.name) {
            val shopViewShop = AdminCURShopViewModel()
            CURShopScreen(navController, "", "C", shopViewShop)
        }
    }
}



