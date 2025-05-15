package com.example.homify.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homify.data.AuthViewModel
import com.example.homify.modelsdata.TenantModel
import com.example.homify.ui.theme.commonscreens.display.SplashScreen
import com.example.homify.ui.theme.commonscreens.login.HomifyLoginScreen
import com.example.homify.ui.theme.commonscreens.register.Register
import com.example.homify.ui.theme.tenantscreens.DashboardScreen
import com.example.homify.ui.theme.tenantscreens.PaymentScreen
import com.example.homify.ui.theme.tenantscreens.ProfileScreen
import com.example.homify.ui.theme.tenantscreens.RentScreen
//import com.example.homify.ui.theme.tenantscreens.UpdateScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("register") { Register(navController) }
        composable("login_home") { HomifyLoginScreen(navController) }
        composable ("dashboard"){ DashboardScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
//        composable ("view"){ ViewProperties(navController) }
        composable("splash") { SplashScreen(navController) }
        composable ("pay"){ PaymentScreen(navController) }
        composable ("rent") { RentScreen(navController) }
//        composable ("update"){ UpdateScreen(navController, tenantId = ) }


    }
}