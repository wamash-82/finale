package com.example.homify.repository

import com.example.homify.modelsdata.TenantModel
import com.google.firebase.database.FirebaseDatabase

class Tenantrepository {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("Tenants")

    fun updateTenant(
        tenantId: String,
        tenant: TenantModel,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        databaseRef.child(tenantId).setValue(tenant)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}