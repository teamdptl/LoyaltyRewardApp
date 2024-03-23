package com.example.loyaltyrewardapp.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loyaltyrewardapp.screens.HistoryPreview
import com.example.loyaltyrewardapp.screens.HistoryScreen
import com.example.loyaltyrewardapp.screens.HomeScreen
import com.example.loyaltyrewardapp.screens.NotificationsPreview
import com.example.loyaltyrewardapp.screens.ProfileContent
import com.example.loyaltyrewardapp.screens.ScanQRContent


@Composable
@Preview
fun AppNavigation(){
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold (
        bottomBar = {
            NavigationBar(
                Modifier.background(Color.Yellow)
            ) {


                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == navItem.route} == true,
                        onClick = {
                            navController.navigate(navItem.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true

                            }
                        },
                        icon = {
                            val iconImage = if (currentDestination?.hierarchy?.any { it.route == navItem.route } == true) {
                                navItem.selectedIcon
                            } else {
                                navItem.unselectedIcon
                            }
                            BadgedBox(
                                badge = {
                                    if (navItem.badgeCount != null) {
                                        Badge {
                                            Text(text = navItem.badgeCount.toString())
                                        }
                                    } else if (navItem.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                if (navItem.label.isEmpty()) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(Color.Yellow, CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = navItem.selectedIcon,
                                            contentDescription = null,
                                            modifier = Modifier.size(35.dp)
                                        )
                                    }
                                } else {
                                    // Hiển thị Icon thông thường
                                    Icon(
                                        imageVector = iconImage,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        },


                        label = {
                            Text(text = navItem.label,   fontSize = 8.sp)
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(route = Screens.HomeScreen.name){
                HomeScreen()
            }
            composable(route = Screens.HistoryScreen.name){
                HistoryPreview()
            }
            composable(route = Screens.ScanQRScreen.name){
                ScanQRContent()
            }
            composable(route = Screens.NotificationsScreen.name){
                NotificationsPreview()
            }
            composable(route = Screens.ProfileActivity.name){
                ProfileContent()
            }
        }
    }
}