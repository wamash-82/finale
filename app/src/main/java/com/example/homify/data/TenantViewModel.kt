package com.example.homify.data

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.homify.modelsdata.TenantModel
import com.example.homify.network.ImgurService
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class TenantViewModel: ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Tenants")
    private val TenantId= FirebaseDatabase.getInstance().getReference("Tenants")
    private val _tenants = MutableStateFlow<List<TenantModel>>(emptyList())
    val tenants: StateFlow<List<TenantModel>> = _tenants
    init {
        loadTenants()
    }

    private fun getImgurService(): ImgurService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ImgurService::class.java)
    }
    private fun getFileFromUri(context: Context, uri: Uri):
            File? {
        return try {
            val inputStream = context.contentResolver
                .openInputStream(uri)
            val file = File.createTempFile("temp_image", ".jpg", context.cacheDir)
            file.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun loadTenants() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tenantList = mutableListOf<TenantModel>()
                for (tenantSnapshot in snapshot.children) {
                    val tenant = tenantSnapshot.getValue(TenantModel::class.java)
                    if (tenant != null) {
                        tenantList.add(tenant)
                    }
                }
                _tenants.value = tenantList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TenantViewModel", "Failed to load tenants: ${error.message}")
            }
        })
    }

    fun uploadTenantWithImage(
        uri: Uri?,
        context: Context,
        name: String,
        age: String,
        phone: String,
        address: String,
        email: String,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val file = getFileFromUri(context, uri!!)
                if (file == null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to process image", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                val reqFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, reqFile)

                val response = getImgurService().uploadImage(
                    body,
                    "Client-ID 589a405a702bbee"
                )

                if (response.isSuccessful) {
                    val imageUrl = response.body()?.data?.link ?: ""

                    val TenantId = database.push().key ?: ""
                    val tenant = TenantModel(
                        name, age, phone, address, email, imageUrl, TenantId
                    )

                    database.child(TenantId).setValue(tenant)
                        .addOnSuccessListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Tenant saved successfully", Toast.LENGTH_SHORT).show()
                                    navController.navigate("profile")
                                }
                            }
                        }.addOnFailureListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Failed to save tenant", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Upload error", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Exception: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
//    fun viewTenants(
//        tenant: MutableState<TenantModel>,
//        tenants: SnapshotStateList<TenantModel>,
//        context: Context
//    ): SnapshotStateList<TenantModel> {
//        val ref = FirebaseDatabase.getInstance().getReference("Tenants")
//
//        ref.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                tenants.clear()
//                for (snap in snapshot.children) {
//                    val value = snap.getValue(TenantModel::class.java)
//                    value?.let {
//                        tenants.add(it)
//                    }
//                }
//                if (tenants.isNotEmpty()) {
//                    tenant.value = tenants.first()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context, "Failed to fetch tenants: ${error.message}", Toast.LENGTH_SHORT).show()
//
//            }
//        })
//
//        return tenants
//    }
    fun updateTenant(context: Context, navController: NavController,
                      name: String, age: String, phone: String, address: String,
                       email: String, TenantId: String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Tenant/$TenantId")
        val updatedTenant = TenantModel(name, age, phone, address, email,
            age,TenantId)

        databaseReference.setValue(updatedTenant)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){

                    Toast.makeText(context,"Tenant Updated Successfully",Toast.LENGTH_LONG).show()
                    navController.navigate("")
                }else{

                    Toast.makeText(context,"Tenant update failed",Toast.LENGTH_LONG).show()
                }
            }

    }
    fun deleteStudent(context: Context,TenantId: String,
                      navController: NavController){
        AlertDialog.Builder(context)
            .setTitle("Delete Tenant")
            .setMessage("Are you sure you want to delete this tenant?")
            .setPositiveButton("Yes"){ _, _ ->
                val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("Tenants/$TenantId")
                databaseReference.removeValue().addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){

                        Toast.makeText(context,"Tenant deleted Successfully",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context,"Tenant not deleted",Toast.LENGTH_LONG).show()
                    }
                }
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
