package com.example.ariandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ariandroid.navigation.NavGraph
import com.example.ariandroid.ui.theme.AriAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AriAndroidTheme {
                NavGraph()
            }
        }
    }
}