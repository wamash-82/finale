package com.example.homify.modelsdata

data class TenantModel(
    var name: String="",
    var age: String="",
    var email: String="",
    var phone: String="",
    var address: String="",
     var imageUrl: String="",
    var TenantId: String="",
    val lastPayment: String = "",
    val unit: String = "",
)
