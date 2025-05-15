package com.example.homify.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.homify.modelsdata.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.State


class AuthViewModel : ViewModel(){
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _currentUserRole = mutableStateOf<String?>(null)
    val currentUserRole: State<String?> = _currentUserRole


    fun register(firstname:String,lastname:String, email: String, password: String, role: String,
               navController: NavController,
               context: Context
    ){
        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank() || role.isBlank()){

            Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_LONG).show()

            return
        }

        _isLoading.value = true

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful){
                    val userId = mAuth.currentUser?.uid ?: ""
                    val userData = UserModel(firstname = firstname, lastname = lastname,
                        email=email,password = password,userId = userId, role = role)
                    saveUserToDatabase(userId,userData,navController,context)


                } else{
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context,"Registration failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun saveUserToDatabase(userId: String, userData: UserModel,
                           navController: NavController, context: Context
    ){
        val regRef = FirebaseDatabase.getInstance()
            .getReference("Users/$userId")
        regRef.setValue(userData).addOnCompleteListener { regRef ->
            if (regRef.isSuccessful){

                Toast.makeText(context,"User Successfully Registered", Toast.LENGTH_LONG).show()
                navController.navigate("login")
            } else{
                _errorMessage.value = regRef.exception?.message

                Toast.makeText(context,"Database error", Toast.LENGTH_LONG).show()

            }
        }
    }
    fun login(email: String,password: String,navController: NavController,
              context: Context){
        if (email.isBlank() || password.isBlank()){

            Toast.makeText(context,"Email and password required",Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful){

                    Toast.makeText(context,"User Successfully logged in",Toast.LENGTH_LONG).show()
                    navController.navigate("dashboard")
                }else{
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context,"Login failed",Toast.LENGTH_LONG).show()

                }
            }
        if (email.contains("tenant")) {
            _currentUserRole.value = "tenant"
        } else {
            _currentUserRole.value = "landlord"
        }
        setUserRole(role = String())
    }
    fun setUserRole(role: String) {
        _currentUserRole.value = role
    }
}
