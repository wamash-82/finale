//package com.example.homify.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.homify.data.AuthViewModel
//import com.example.homify.ui.theme.commonscreens.login.HomifyLoginScreen
//import com.example.homify.ui.theme.commonscreens.register.Register
//
//@Composable
//fun HomifyApp(navController: NavController) {
//    val authViewModel: AuthViewModel = viewModel()
//    val userRole = authViewModel.currentUserRole.value// "tenant" or "landlord"
//
//    if (userRole == "tenant") {
//        Navigation()
//    } else if (userRole == "landlord") {
////        Navigation2()
//    } else {
//        Register(navController) // login/register
//    }
//}
