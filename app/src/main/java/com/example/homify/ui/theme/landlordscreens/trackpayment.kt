//package com.example.homify.ui.theme.landlordscreens
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun PaymentsScreen2(navController: NavController) {
//    Scaffold(
////        topBar = {
////            TopAppBar(
////                title = { Text("Homify - Payments Overview") },
////                navigationIcon = {
////                    IconButton(onClick = { navController.popBackStack() }) {
////                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
////                    }
////                },
////                colors = topAppBarColors(containerColor = Color(0xFF1976D2))
////            )
////        }
//    ) { padding ->
//        val payments = remember {
//            listOf(
//                Payment("John Doe", "Apartment A3", "2025-05-01", "Paid", 1200.0),
//                Payment("Jane Smith", "House B5", "2025-05-03", "Pending", 950.0),
//                Payment("Alice Brown", "Studio C2", "2025-04-30", "Paid", 780.0)
//            )
//        }
//
//        LazyColumn(
//            modifier = Modifier
//                .padding(padding)
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(payments) { payment ->
//                Card(
//                    elevation = CardDefaults.cardElevation(6.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = if (payment.status == "Paid") Color(0xFF4CAF50) else Color(0xFFFF9800)
//                    )
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text("Tenant: ${payment.tenantName}", fontWeight = FontWeight.Bold, color = Color.White)
//                        Text("Property: ${payment.propertyName}", color = Color.White)
//                        Text("Amount: $${payment.amount}", color = Color.White)
//                        Text("Date: ${payment.date}", color = Color.White)
//                        Text("Status: ${payment.status}", fontWeight = FontWeight.SemiBold, color = Color.White)
//                    }
//                }
//            }
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun PaymentPreview() {
//    PaymentsScreen2(navController = rememberNavController())
//}
