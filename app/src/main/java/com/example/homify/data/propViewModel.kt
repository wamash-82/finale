//package com.example.homify.data
//
//import android.util.Property
//import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlin.collections.filter
//import kotlin.collections.map
//
//class propViewModel : ViewModel(){
//    private val _propertyList = MutableStateFlow<List<Property>>(emptyList())
//    val propertyList: StateFlow<List<Property>> = _propertyList
//
//    init {
//        // Mock data
//        _propertyList.value = listOf(
//            Property("1", "Hillview Apartments", "Kilimani", "Available"),
//            Property("2", "Green Villa", "Westlands", "Occupied")
//        )
//    }
//
//    fun toggleStatus(property: Property) {
//        _propertyList.value = _propertyList.value.map {
//            if (it.id == property.id)
//                it.copy(status = if (it.status == "Available") "Occupied" else "Available")
//            else it
//        }
//    }
//
//    fun addProperty(property: Property) {
//        _propertyList.value = _propertyList.value + property
//    }
//
//    fun deleteProperty(id: String) {
//        _propertyList.value = _propertyList.value.filter { it.id != id }
//    }
//}
//}