package com.example.homify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.homify.navigation.HomifyApp
import com.example.homify.ui.theme.HomifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomifyTheme {
              HomifyApp() // This pulls in your NavHost/navigation
            }
        }
    }
}