package com.example.tp1_applicationfacture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tp1_applicationfacture.ui.theme.TP1ApplicationFactureTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP1ApplicationFactureTheme {
                Nav()
            }
        }
    }
}