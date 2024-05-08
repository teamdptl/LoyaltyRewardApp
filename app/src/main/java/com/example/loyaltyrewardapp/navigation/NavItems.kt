package com.example.loyaltyrewardapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String
)

val listOfUserNavItems: List<NavItems> = listOf(
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

val listOfManagerNavItems: List<NavItems> = listOf(
    NavItems(
        label = "Trang chủ",
        selectedIcon = Icons.Outlined.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        route = Screens.HomeManagerScreen.name
    ),
    NavItems(
        label = "Cửa hàng",
        selectedIcon = Icons.Outlined.Shop,
        unselectedIcon = Icons.Outlined.Shop,
        hasNews = false,
        route = Screens.ShopManagerScreen.name
    ),
    NavItems(
        label = "",
        selectedIcon = Icons.Outlined.QrCodeScanner,
        unselectedIcon = Icons.Outlined.QrCodeScanner,
        hasNews = false,
        route = Screens.ScanManagerScreen.name
    ),
    NavItems(
        label = "Thông báo",
        selectedIcon = Icons.Outlined.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        hasNews = false,
        route = Screens.NotificationManagerScreen.name
    ),
    NavItems(
        label = "Tài khoản",
        selectedIcon = Icons.Outlined.Person,
        unselectedIcon = Icons.Outlined.Person,
        hasNews = false,
        route = Screens.ProfileActivity.name
    ),
)