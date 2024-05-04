package com.example.loyaltyrewardapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loyaltyrewardapp.ui.LoginScreen

@Composable
fun GuestNavigation(isLogin: Boolean = false) {
    val navController = rememberNavController()
    var startDestination = Screens.LoginScreen.name
    if (isLogin) {
        startDestination = Screens.AppNavigationScreen.name
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.LoginScreen.name) {
            LoginScreen()
        }
        composable(route = Screens.AppNavigationScreen.name) {
            AppNavigation()
        }
    }
}