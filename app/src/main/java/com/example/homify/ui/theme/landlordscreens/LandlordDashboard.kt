package com.example.homify.ui.theme.landlordscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard2(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Homify - Landlord Dashboard", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF008080))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome, Landlord!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF008080)
            )

            LandlordDashboardCard(
                label = "Add New Property",
                icon = Icons.Default.Add,
                onClick = { navController.navigate("addProperty") },
                cardColor = Color(0xFF26C6DA)
            )
            LandlordDashboardCard(
                label = "Manage Properties",
                icon = Icons.Default.Home,
                onClick = { navController.navigate("propertyList") },
                cardColor = Color(0xFF26A69A)
            )
            LandlordDashboardCard(
                label = "Track Payments",
                icon = Icons.Default.DateRange,
                onClick = { navController.navigate("paymentTracking") },
                cardColor = Color(0xFFAB47BC)
            )
            LandlordDashboardCard(
                label = "View Tenants",
                icon = Icons.Default.Person,
                onClick = { navController.navigate("viewTenants") },
                cardColor = Color(0xFFFF7043)
            )
        }
    }
}

@Composable
fun LandlordDashboardCard(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    cardColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = cardColor),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(8.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(36.dp))
            Spacer(Modifier.height(6.dp))
            Text(label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
    Spacer(Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun LordPreview() {
    Dashboard2(navController = rememberNavController())
}
