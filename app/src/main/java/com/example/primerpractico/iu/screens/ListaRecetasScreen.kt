package com.example.primerpractico.recetas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.primerpractico.model.Ingrediente
import com.example.primerpractico.model.Receta
import com.example.primerpractico.viewmodel.RecetaViewModel


@Composable
fun ListaRecetasScreen(
    viewModel: RecetaViewModel,
    onNavigateToCrear: () -> Unit
) {

    val recetas by viewModel.recetas.collectAsState()
    val busqueda by viewModel.busqueda.collectAsState()

    val recetasFiltradas = recetas.filter {
        it.nombre.lowercase().contains(busqueda.lowercase())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = busqueda,
            onValueChange = { viewModel.actualizarBusqueda(it) },
            label = { Text("Buscar receta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onNavigateToCrear() }) {
            Text("Crear receta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recetasFiltradas) { receta ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = receta.nombre)

                    receta.ingredientes.forEach {
                        Text("- ${it.nombre}: ${it.cantidad}")
                    }
                }
            }
        }
    }
}

