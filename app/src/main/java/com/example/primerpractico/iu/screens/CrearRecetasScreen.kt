package com.example.primerpractico.recetas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.primerpractico.model.Ingrediente
import com.example.primerpractico.model.Receta
import com.example.primerpractico.viewmodel.RecetaViewModel

@Composable
fun CrearRecetaScreen(
    viewModel: RecetaViewModel,
    onBack: () -> Unit
) {

    var nombre by remember { mutableStateOf("") }
    var nombreIngrediente by remember { mutableStateOf("") }
    var cantidadIngrediente by remember { mutableStateOf("") }

    val ingredientes = remember { mutableStateListOf<Ingrediente>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Crear Receta", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ingredientes", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = nombreIngrediente,
            onValueChange = { nombreIngrediente = it },
            label = { Text("Ingrediente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = cantidadIngrediente,
            onValueChange = { cantidadIngrediente = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nombreIngrediente.isNotBlank() && cantidadIngrediente.isNotBlank()) {
                    ingredientes.add(
                        Ingrediente(nombreIngrediente, cantidadIngrediente)
                    )
                    nombreIngrediente = ""
                    cantidadIngrediente = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Agregar ingrediente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(ingredientes) { ingrediente ->
                Text(
                    text = "- ${ingrediente.nombre}: ${ingrediente.cantidad}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Button(
            onClick = {
                if (nombre.isNotBlank() && ingredientes.isNotEmpty()) {
                    viewModel.agregarReceta(
                        Receta(nombre, ingredientes.toList())
                    )
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar receta")
        }
    }
}