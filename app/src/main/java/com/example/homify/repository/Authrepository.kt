package com.example.homify.repository

import com.google.firebase.auth.FirebaseAuth


class Authrepository {
    private val auth: FirebaseAuth= FirebaseAuth.getInstance()

    fun register(email: String,password: String,
                 onResult: (Boolean)->Unit){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }
    fun login(email: String,password: String,onResult: (Boolean) -> Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                onResult(task.isSuccessful)
            }
    }
    fun logout(){
        auth.signOut()
    }
}
