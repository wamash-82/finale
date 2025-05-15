package com.example.homify.ui.theme.landlordscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Dashboard2(navController: NavController) {
    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Homify - Landlord Dashboard") },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2)),
//                actions = {
//                    IconButton(onClick = { /* handle logout */ }) {
//                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
//                    }
//                }
//            )
//        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome, Landlord!", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Button(
                onClick = { navController.navigate("addProperty") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Property")
                Spacer(Modifier.width(8.dp))
                Text("Add New Property")
            }

            Button(
                onClick = { navController.navigate("propertyList") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Home, contentDescription = "View Properties")
                Spacer(Modifier.width(8.dp))
                Text("Manage Properties")
            }

            Button(
                onClick = { navController.navigate("paymentTracking") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.DateRange, contentDescription = "Track Payments")
                Spacer(Modifier.width(8.dp))
                Text("Track Payments")
            }

            Button(
                onClick = { navController.navigate("viewTenants") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Person, contentDescription = "View Tenants")
                Spacer(Modifier.width(8.dp))
                Text("View Tenants")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LordPreview() {
    Dashboard2(navController = rememberNavController())
}
