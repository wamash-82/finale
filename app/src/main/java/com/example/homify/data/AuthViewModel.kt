//package com.example.homify.data
//
//import android.content.Context
//import android.widget.Toast
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.navigation.NavController
//import com.example.homify.modelsdata.UserModel
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase
//import kotlinx.coroutines.flow.MutableStateFlow
//import androidx.compose.runtime.State
//
//
//class AuthViewModel : ViewModel(){
//    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val _isLoading = MutableStateFlow(false)
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    private val _currentUserRole = mutableStateOf<String?>(null)
//    val currentUserRole: State<String?> = _currentUserRole
//
//
//    fun register(firstname:String,lastname:String, email: String, password: String, role: String,
//               navController: NavController,
//               context: Context
//    ){
//        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank() || role.isBlank()){
//
//            Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_LONG).show()
//
//            return
//        }
//
//        _isLoading.value = true
//
//        mAuth.createUserWithEmailAndPassword(email,password)
//            .addOnCompleteListener { task ->
//                _isLoading.value = false
//                if (task.isSuccessful){
//                    val userId = mAuth.currentUser?.uid ?: ""
//                    val userData = UserModel(firstname = firstname, lastname = lastname,
//                        email=email,password = password,userId = userId, role = role)
//                    saveUserToDatabase(userId,userData,navController,context)
//
//
//                } else{
//                    _errorMessage.value = task.exception?.message
//
//                    Toast.makeText(context,"Registration failed", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
//
//    fun saveUserToDatabase(userId: String, userData: UserModel,
//                           navController: NavController, context: Context
//    ){
//        val regRef = FirebaseDatabase.getInstance()
//            .getReference("Users/$userId")
//        regRef.setValue(userData).addOnCompleteListener { regRef ->
//            if (regRef.isSuccessful){
//
//                Toast.makeText(context,"User Successfully Registered", Toast.LENGTH_LONG).show()
//                navController.navigate("login")
//            } else{
//                _errorMessage.value = regRef.exception?.message
//
//                Toast.makeText(context,"Database error", Toast.LENGTH_LONG).show()
//
//            }
//        }
//    }
//    fun login(email: String,password: String,navController: NavController,
//              context: Context){
//        if (email.isBlank() || password.isBlank()){
//
//            Toast.makeText(context,"Email and password required",Toast.LENGTH_LONG).show()
//            return
//        }
//        _isLoading.value = true
//
//        mAuth.signInWithEmailAndPassword(email,password)
//            .addOnCompleteListener { task ->
//                _isLoading.value = false
//                if (task.isSuccessful){
//
//                    Toast.makeText(context,"User Successfully logged in",Toast.LENGTH_LONG).show()
//                    navController.navigate("dashboard")
//                }else{
//                    _errorMessage.value = task.exception?.message
//
//                    Toast.makeText(context,"Login failed",Toast.LENGTH_LONG).show()
//
//                }
//            }
//        if (email.contains("tenant")) {
//            _currentUserRole.value = "tenant"
//        } else {
//            _currentUserRole.value = "landlord"
//        }
//        setUserRole(role = String())
//    }
//    fun setUserRole(role: String) {
//        _currentUserRole.value = role
//    }
//}
//package com.example.homify.data
//
//import android.content.Context
//import android.widget.Toast
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.navigation.NavController
//import com.example.homify.modelsdata.UserModel
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase
//import kotlinx.coroutines.flow.MutableStateFlow
//import androidx.compose.runtime.State
//
//class AuthViewModel : ViewModel() {
//    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val _isLoading = MutableStateFlow(false)
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    private val _currentUserRole = mutableStateOf<String?>(null)
//    val currentUserRole: State<String?> = _currentUserRole
//    private val _registrationSuccess = mutableStateOf(false)
//    val registrationSuccess: State<Boolean> = _registrationSuccess
//    fun register(
//        firstname: String,
//        lastname: String,
//        email: String,
//        password: String,
//        role: String,
//        navController: NavController,
//        context: Context
//    ) {
//        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank() || role.isBlank()) {
//            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
//            return
//        }
//
//        _isLoading.value = true
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                _isLoading.value = false
//                if (task.isSuccessful) {
//                    val userId = mAuth.currentUser?.uid ?: ""
//                    val userData = UserModel(
//                        firstname = firstname,
//                        lastname = lastname,
//                        email = email,
//                        password = password,
//                        userId = userId,
//                        role = role
//                    )
//                    saveUserToDatabase(userId, userData, navController, context, role)
//                } else {
//                    _errorMessage.value = task.exception?.message
//                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
//
//    fun saveUserToDatabase(
//        userId: String,
//        userData: UserModel,
//        navController: NavController,
//        context: Context,
//        role: String
//    ) {
//        val regRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
//        regRef.setValue(userData).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
//
//                setUserRole(role)
//
//                when (role.lowercase()) {
//                    "tenant" -> navController.navigate("tenant_dashboard")
//                    "landlord" -> navController.navigate("landlord_dashboard")
//                    else -> navController.navigate("dashboard")
//                }
//            } else {
//                _errorMessage.value = task.exception?.message
//                Toast.makeText(context, "Database error", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//
//    fun login(
//        email: String,
//        password: String,
//        navController: NavController,
//        context: Context
//    ) {
//        if (email.isBlank() || password.isBlank()) {
//            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
//            return
//        }
//
//        _isLoading.value = true
//
//        mAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                _isLoading.value = false
//                if (task.isSuccessful) {
//                    val userId = mAuth.currentUser?.uid ?: return@addOnCompleteListener
//                    val dbRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
//
//                    dbRef.get().addOnSuccessListener { snapshot ->
//                        val role = snapshot.child("role").value?.toString()?.lowercase()
//                        setUserRole(role ?: "")
//                        Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
//
//                        when (role) {
//                            "tenant" -> navController.navigate("tenant_dashboard")
//                            "landlord" -> navController.navigate("landlord_dashboard")
//                            else -> navController.navigate("dashboard")
//                        }
//                    }.addOnFailureListener {
//                        Toast.makeText(context, "Error fetching user role", Toast.LENGTH_LONG).show()
//                        navController.navigate("dashboard") // fallback
//                    }
//                } else {
//                    _errorMessage.value = task.exception?.message
//                    Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
//
//    fun setUserRole(role: String) {
//        _currentUserRole.value = role
//    }
//}
package com.example.homify.data

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.homify.modelsdata.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.runtime.State
import com.example.homify.navigation.ROUTEDASHBOARD
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _currentUserRole = mutableStateOf<String?>(null)
    val currentUserRole: State<String?> = _currentUserRole
    private val _registrationSuccess = mutableStateOf(false)
    val registrationSuccess: State<Boolean> = _registrationSuccess

    fun register(
        firstname: String,
        lastname: String,
        email: String,
        password: String,
        role: String,
        navController: NavController,
        context: Context
    ) {
        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank() || role.isBlank()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid ?: ""
                    val userData = UserModel(
                        firstname = firstname,
                        lastname = lastname,
                        email = email,
                        password = password,
                        userId = userId,
                        role = role
                    )
                    saveUserToDatabase(userId, userData, context)
                    _registrationSuccess.value = true
                    setUserRole(role)
                } else {
                    Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    _registrationSuccess.value = false
                }
            }
    }

    private fun saveUserToDatabase(
        userId: String,
        userData: UserModel,
        context: Context
    ) {
        val regRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
        regRef.setValue(userData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Database error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

//    fun login(
//        email: String,
//        password: String,
//        navController: NavController,
//        context: Context
//    ) {
//        if (email.isBlank() || password.isBlank()) {
//            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
//            return
//        }
//
//        mAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val userId = mAuth.currentUser?.uid ?: return@addOnCompleteListener
//                    val dbRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
//                    dbRef.get().addOnSuccessListener { snapshot ->
//                        val role = snapshot.child("role").value?.toString()?.lowercase()
//                        setUserRole(role ?: "")
//                        Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
//                        when (role) {
//                            "tenant" -> navController.navigate(ROUTEDASHBOARD)
//                            "landlord" -> navController.navigate("dashboard2")
//                            else -> navController.navigate("dashboard")
//                        }
//                    }.addOnFailureListener {
//                        Toast.makeText(context, "Error fetching user role", Toast.LENGTH_LONG).show()
//                        navController.navigate("dashboard")
//                    }
//                } else {
//                    Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
@RequiresApi(Build.VERSION_CODES.O)
fun login(email: String, password: String, navController: NavController, context: Context) {
    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Fetch user role from your database (e.g., Firestore)
                val userId = mAuth.currentUser?.uid
                val userDoc = FirebaseFirestore.getInstance().collection("users").document(userId!!)
                userDoc.get().addOnSuccessListener { document ->
                    val role = document.getString("role")
                    when (role) {
                        "tenant" -> navController.navigate(ROUTEDASHBOARD) {
                            popUpTo("login") { inclusive = true }
                        }
                        "landlord" -> navController.navigate("dashboard2") {
                            popUpTo("login") { inclusive = true }
                        }
                        else -> Toast.makeText(context, "Unknown role", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
            }
        }
}
    fun setUserRole(role: String?) {
        _currentUserRole.value = role
    }
    fun logout(navController: NavController, context: Context) {
        mAuth.signOut()
        setUserRole(null)
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
        navController.navigate("login") {
            popUpTo(0) { inclusive = true }
        }
    }
}