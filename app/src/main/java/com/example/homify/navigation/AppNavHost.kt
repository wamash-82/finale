package com.example.homify.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homify.data.AuthViewModel
import com.example.homify.ui.theme.commonscreens.display.SplashScreen
import com.example.homify.ui.theme.commonscreens.login.HomifyLoginScreen
import com.example.homify.ui.theme.commonscreens.register.Register
import com.example.homify.ui.theme.landlordscreens.Dashboard2
import com.example.homify.ui.theme.landlordscreens.ViewScreen
import com.example.homify.ui.theme.tenantscreens.DashboardScreen
import com.example.homify.ui.theme.tenantscreens.PaymentScreen
import com.example.homify.ui.theme.tenantscreens.ProfileScreen
import com.example.homify.ui.theme.tenantscreens.RentScreen

@Composable
fun HomifyApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("register") { Register(navController) }
        composable(ROUTELOGIN) {
            HomifyLoginScreen(navController)
        }

        // Always add all routes
        composable(ROUTEDASHBOARD) {
            DashboardScreen(navController)
        }
        composable("profile") { ProfileScreen(navController) }
        composable("pay") { PaymentScreen(navController) }
        composable("rent") { RentScreen(navController) }
        composable("dashboard2") { Dashboard2(navController) }
        composable("viewtenants") { ViewScreen(navController) }
        // Add more landlord routes here if needed
    }
}