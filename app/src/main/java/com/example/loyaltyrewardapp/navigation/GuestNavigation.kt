package com.example.loyaltyrewardapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.data.model.NotFoundUserState
import com.example.loyaltyrewardapp.data.model.User
import com.example.loyaltyrewardapp.data.model.UserEmptyState
import com.example.loyaltyrewardapp.data.viewmodel.GuestViewModel
import com.example.loyaltyrewardapp.data.viewmodel.UserHomeViewModel
import com.example.loyaltyrewardapp.screens.SplashScreen
import com.example.loyaltyrewardapp.screens.registerScreen
import com.example.loyaltyrewardapp.ui.LoginScreen
import kotlinx.coroutines.delay

@Composable
fun GuestNavigation(viewModel : GuestViewModel = GuestViewModel()) {

    val user by viewModel.user.observeAsState()
    val navController = rememberNavController()

    LaunchedEffect(null) {
        delay(2000)
        viewModel.fetchCurrentUser()
    }

    var startDestination = Screens.SplashScreen.name

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
        startDestination = Screens.ManagerNavigationScreen.name
    }
    else if (user?.role == "user") {
        startDestination = Screens.UserNavigationScreen.name
    }

//    Log.d("TAG", "GuestNavigation: $startDestination")

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.SplashScreen.name){
            SplashScreen()
        }
        composable(route = Screens.LoginScreen.name) {
            LoginScreen(navController)
        }
        composable(route = Screens.registerScreen.name) {
            registerScreen(navController)
        }
        composable(route = Screens.OTPVerificationScreen.name) {
//            OTPVerificationScreen(navController)
        }
        composable(route = Screens.UserNavigationScreen.name) {
            UserNavigation()
        }
        composable(route = Screens.ManagerNavigationScreen.name) {
            ManagerNavigation()
        }
    }
}