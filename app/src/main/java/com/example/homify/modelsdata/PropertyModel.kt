package com.example.homify.modelsdata

data class PaymentModel(
    val tenantName: String,
    val propertyName: String,
    val amount: Double,
    val date: String,
    val status: String
)