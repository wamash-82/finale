package com.example.homify.ui.theme.tenantscreens

//import tenantViewModel
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.homify.R
import com.example.homify.data.TenantViewModel
import com.example.homify.modelsdata.TenantModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun UpdateScreen(navController: NavController, tenantId: String) {
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let { imageUri.value = it } }
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    val tenantViewModel: TenantViewModel = viewModel()
    val context = LocalContext.current
    val currentDataRef = FirebaseDatabase.getInstance()
        .getReference().child("tenants/$tenantId")

    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tenant = snapshot.getValue(TenantModel::class.java)
                tenant?.let {
                    name = it.name
                    age = it.age
                    phone = it.phone
                    address = it.address
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }
    Column(
        modifier = Modifier.padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Red).padding(16.dp)
        ) {
            Text(
                text = "UPDATE tenant",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Card(shape = CircleShape, modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(
                model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
                    .clickable { launcher.launch("image/*") }
            )
        }
        Text(text = "Upload Picture Here")

        OutlinedTextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = { Text(text = "Enter tenant name") },
            placeholder = { Text(text = "PLease enter tenant name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age,
            onValueChange = { newage -> age = newage },
            label = { Text(text = "Enter tenant age") },
            placeholder = { Text(text = "PLease enter tenant age") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { newphone -> phone = newphone },
            label = { Text(text = "Enter tenant phone") },
            placeholder = { Text(text = "PLease enter tenant phone") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address,
            onValueChange = { newaddress -> address = newaddress },
            label = { Text(text = "Enter tenant address") },
            placeholder = { Text(text = "PLease enter tenant address") },
            modifier = Modifier.fillMaxWidth().height(150.dp), singleLine = false
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "GO BACK")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                tenantViewModel.updateTenant(
                    context = context,
                    navController = navController,
                    name = name,
                    age = age,
                    phone = phone,
                    address = address,
                    email = "",
                    TenantId = tenantId
                )
            }) {
                Text(text = "UPDATE")
            }
        }
    }
}