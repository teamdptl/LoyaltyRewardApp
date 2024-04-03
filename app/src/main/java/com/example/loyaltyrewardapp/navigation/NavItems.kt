package com.example.loyaltyrewardapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String
)

val listOfNavItems: List<NavItems> = listOf(
    NavItems(
        label = "Trang chủ",
        selectedIcon = Icons.Outlined.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        route = Screens.HomeScreen.name
    ),
    NavItems(
        label = "Ưu đãi",
        selectedIcon = Icons.Outlined.ConfirmationNumber,
        unselectedIcon = Icons.Outlined.ConfirmationNumber,
        hasNews = false,
        route = Screens.CouponScreen.name
    ),
    NavItems(
        label = "",
        selectedIcon = Icons.Outlined.QrCodeScanner,
        unselectedIcon = Icons.Outlined.QrCodeScanner,
        hasNews = false,
        route = Screens.ScanQRScreen.name
    ),
//    NavItems(
//        label = "Thông báo",
//        selectedIcon = Icons.Filled.Notifications,
//        unselectedIcon = Icons.Outlined.Notifications,
//        hasNews = false,
//        badgeCount = 45,
//        route = Screens.NotificationsScreen.name
//    ),
    NavItems(
        label = "Lịch sử",
        selectedIcon = Icons.Outlined.History,
        unselectedIcon = Icons.Outlined.History,
        hasNews = false,
        route = Screens.HistoryScreen.name
    ),
    NavItems(
        label = "Tài khoản",
        selectedIcon = Icons.Outlined.Person,
        unselectedIcon = Icons.Outlined.Person,
        hasNews = false,
        route = Screens.ProfileActivity.name
    ),
)