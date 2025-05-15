package com.example.homify.ui.theme.tenantscreens

import android.content.pm.PackageManager.Property

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homify.R
//
//@Composable
//fun ViewProperties(navController: NavController) {
//    val propertyList = listOf(
//        Property("Greenview Apartments", "2 Bed • Kileleshwa", "KES 45,000", R.drawable.home),
//        Property("Palm Gardens", "1 Bed • Westlands", "KES 35,000", R.drawable.home),
//        Property("Serene Villas", "3 Bed • Lavington", "KES 70,000", R.drawable.home)
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Available Properties") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF008080),
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White
//                )
//            )
//        }
//    ) { padding ->
//        LazyColumn(
//            contentPadding = padding,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            items(propertyList) { property ->
//                PropertyCard(property)
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun ViewPropertiesPreview() {
//    ViewProperties(rememberNavController())
//}
