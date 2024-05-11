package com.example.loyaltyrewardapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loyaltyrewardapp.screens.*


@Composable
@Preview
fun UserNavigation(){
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold (
        bottomBar = {
            if (shouldShowBottomBar(navController)){
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 3.dp,
                    modifier = Modifier
                        .shadow(12.dp)
                        .height(80.dp),
                ) {

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    listOfUserNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any {it.route == navItem.route} == true,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.White,
                                unselectedIconColor = Color(0xFFB4B4B4),
                                unselectedTextColor = Color(0xFFB4B4B4),
                                selectedIconColor = Color(0xFFFF6E2E),
                                selectedTextColor = Color(0xFFFF6E2E)
                            ),
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
                                                .background(Color(0xFFFEE930), RoundedCornerShape(15.dp))
                                        ) {
                                            Icon(
                                                imageVector = navItem.selectedIcon,
                                                contentDescription = null,
                                                tint = Color(0xFF636363),
                                                modifier = Modifier.size(35.dp)

                                            )
                                        }
                                    } else {
                                        // Hiển thị Icon thông thường
                                        Icon(
                                            imageVector = iconImage,
                                            contentDescription = null,
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }
                            },

                            label = {
                                if (navItem.label.isNotEmpty()) {
                                    Text(text = navItem.label, fontSize = 11.sp)
                                }
                            }
                        )
                    }
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
                HomeScreen(navController)
            }
            composable(route = Screens.CouponScreen.name){
                ListDiscountCoupon(navController)
            }
            composable(route = Screens.HistoryScreen.name){
                HistoryPreview()
            }
            composable(route = Screens.ProfileActivity.name){
                ProfileContent(navController)
            }
            composable(route = Screens.ShopVerticalScreen.name){
                CompaniesListVerticalScreen(navController)
            }

            composable(route = Screens.DetailShopScreen.name + "/{shopId}"){ backStackEntry ->
                DetailCompany(navController, backStackEntry.arguments?.getString("shopId"))
            }

            composable(route = Screens.DetailCouponScreen.name + "/{couponId}"){ backStackEntry ->
                DetailCoupon(navController, backStackEntry.arguments?.getString("couponId"))
            }

            composable(route = Screens.UserCouponQR.name + "/{couponId}"){backStackEntry ->
                UserCouponQR(navController, backStackEntry.arguments?.getString("couponId"))
            }

        }
    }
}

@Composable
fun shouldShowBottomBar(navController: NavController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Hide bottom navigation bar on specific screens by returning false
    return currentDestination?.route in listOf(
        Screens.HomeScreen.name,
        Screens.CouponScreen.name,
        Screens.HistoryScreen.name,
        Screens.ProfileActivity.name,
    )
}