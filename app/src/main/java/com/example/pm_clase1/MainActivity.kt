package com.example.pm_clase1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.pm_clase1.interfaces.AppNav
import com.example.pm_clase1.interfaces.AudioScreen
import com.example.pm_clase1.interfaces.CameraScreen
import com.example.pm_clase1.interfaces.PermissionsDemoScreen
import com.example.pm_clase1.ui.theme.PM_Clase1Theme
import java.security.Permission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraScreen()
            }
        }
    }