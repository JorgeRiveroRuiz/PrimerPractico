package com.example.primerpractico.recetas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.primerpractico.viewmodel.RecetaViewModel


@Composable
fun ListaRecetasScreen(
    viewModel: RecetaViewModel,
    onNavigateToCrear: () -> Unit,
    onNavigateToPlan: () -> Unit,
    onNavigateToCompras: () -> Unit
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

        Button(
            onClick = { onNavigateToCrear() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear receta")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToPlan() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Plan semanal")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToCompras() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lista de compras")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recetasFiltradas) { receta ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = receta.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        receta.ingredientes.forEach {
                            Text("- ${it.nombre}: ${it.cantidad}")
                        }
                    }
                }
            }
        }

    }
}
