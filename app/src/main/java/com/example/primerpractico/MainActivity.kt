package com.example.primerpractico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.primerpractico.ui.theme.PrimerPracticoTheme
import com.example.primerpractico.viewmodel.RecetaViewModel
import com.example.recetas.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    private val viewModel = RecetaViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation(viewModel)
        }
    }
}

