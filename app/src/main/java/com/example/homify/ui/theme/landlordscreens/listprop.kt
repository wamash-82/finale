//package com.example.homify.ui.theme.landlordscreens
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.homify.data.propViewModel
//import com.example.homify.ui.theme.tenantscreens.DashboardScreen
//
//@Composable
//fun LandlordScreen(navController: NavController, viewModel: propViewModel) {
//    val properties by viewModel.propertyList.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("My Properties") },
//                navigationIcon = {
//                    IconButton(onClick = { /* Optional: Logout or Back */ }) {
//                        Icon(Icons.Default.Home, contentDescription = "Home")
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00695C))
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { navController.navigate("add_property") },
//                containerColor = Color(0xFF00695C)
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add Property")
//            }
//        }
//    ) { padding ->
//        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
//            items(properties) { property ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp),
//                    elevation = CardDefaults.cardElevation(8.dp)
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text("Name: ${property.name}", fontWeight = FontWeight.Bold)
//                        Text("Location: ${property.location}")
//                        Text("Status: ${property.status}")
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Button(onClick = {
//                                viewModel.toggleStatus(property)
//                            }) {
//                                Text("Toggle Status")
//                            }
//                            Button(
//                                onClick = { viewModel.deleteProperty(property.id) },
//                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//                            ) {
//                                Text("Delete", color = Color.White)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun LandLordPreview() {
//    LandlordScreen(navController = rememberNavController())
//}
