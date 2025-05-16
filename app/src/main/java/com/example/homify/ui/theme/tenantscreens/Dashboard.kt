package com.example.homify.ui.theme.tenantscreens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.homify.R
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val selectedItem = remember { mutableStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF008080)) {
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = {
                        selectedItem.value = 0
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "Check out Homify! https://homify.com")
                        }
                        context.startActivity(Intent.createChooser(shareIntent, null))
                    },
                    icon = { Icon(Icons.Default.Share, contentDescription = "Share") },
                    label = { Text("Share") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = {
                        selectedItem.value = 1
                        val callIntent = Intent(Intent.ACTION_DIAL).apply {
                            data = "tel:0742904964".toUri()
                        }
                        context.startActivity(callIntent)
                    },
                    icon = { Icon(Icons.Default.Phone, contentDescription = "Call Mgmt") },
                    label = { Text("Call Mgmt") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = {
                        selectedItem.value = 2
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = "mailto:support@homify.com".toUri()
                            putExtra(Intent.EXTRA_SUBJECT, "Maintenance Request")
                        }
                        context.startActivity(emailIntent)
                    },
                    icon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                    label = { Text("Email") },
                    alwaysShowLabel = true
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Stretch background image to fill the screen
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "background image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    title = { Text("Tenant Dashboard", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("profile") }) {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xCC008080),
                        titleContentColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Grid of Cards
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DashboardCard(
                            label = "My Rent",
                            icon = Icons.Default.Home,
                            onClick = { navController.navigate("rent") },
                            modifier = Modifier.weight(1f)
                        )
                        DashboardCard(
                            label = "Maintenance",
                            icon = Icons.Default.Email,
                            onClick = { navController.navigate("maintenance") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DashboardCard(
                            label = "Notices",
                            icon = Icons.Default.Close,
                            onClick = { navController.navigate("notices") },
                            modifier = Modifier.weight(1f)
                        )
                        DashboardCard(
                            label = "Contact Mgmt",
                            icon = Icons.Default.Phone,
                            onClick = {
                                val callIntent = Intent(Intent.ACTION_DIAL, "tel:0700123456".toUri())
                                context.startActivity(callIntent)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        DashboardCard(
                            label = "Help",
                            icon = Icons.Default.Email,
                            onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = "mailto:support@homify.com".toUri()
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier.weight(1f)
                        )
                        DashboardCard(
                            label = "Profile",
                            icon = Icons.Default.Person,
                            onClick = { navController.navigate("profile") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardCard(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xCC008080))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DashboardPreview() {
//    DashboardScreen(navController = rememberNavController())
//}
