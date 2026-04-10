package com.example.pm_clase1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.pm_clase1.interfaces.AppNav
import com.example.pm_clase1.ui.theme.PM_Clase1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PM_Clase1Theme {
                     Surface{
                        val navController = rememberNavController()
                        AppNav(navController)
                    }
                }
            }
        }
    }


