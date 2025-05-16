//package com.example.homify.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.homify.data.AuthViewModel
//import com.example.homify.ui.theme.commonscreens.login.HomifyLoginScreen
//import com.example.homify.ui.theme.commonscreens.register.Register
//import com.example.homify.ui.theme.landlordscreens.Dashboard2
//
//@Composable
//fun Navigation2() {
//    val navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = "splash"
//    ) {
//        composable("login") {HomifyLoginScreen(navController)}
//        composable ("register"){ Register(navController) }
//        composable ("dashboard2"){ Dashboard2(navController) }
////        composable ("listproperty"){ LandlordScreen(navController) }
//        composable ("track"){  }
//    }
//}