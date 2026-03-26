package com.fadymarty.gametime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fadymarty.gametime.presentation.navigation.NavigationRoot
import com.fadymarty.uikit.common.theme.GameTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavigationRoot()
                }
            }
        }
    }
}