package com.example.homify.ui.theme.landlordscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homify.data.TenantViewModel

@Composable
fun ViewScreen(navController: NavController, tenantViewModel: TenantViewModel = viewModel()) {
    val tenants = tenantViewModel.tenants.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Tenant Payments", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Name", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelMedium)
            Text("Unit", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelMedium)
            Text("Phone", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelMedium)
            Text("Last Payment", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelMedium)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        tenants.forEach { tenant ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(tenant.name, modifier = Modifier.weight(1f))
                Text(tenant.unit, modifier = Modifier.weight(1f))
                Text(tenant.phone, modifier = Modifier.weight(1f))
                Text(tenant.lastPayment, modifier = Modifier.weight(1f))
            }
            Divider(modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}