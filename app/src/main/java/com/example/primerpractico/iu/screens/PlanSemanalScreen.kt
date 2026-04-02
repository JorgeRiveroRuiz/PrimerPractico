package com.example.primerpractico.iu.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.primerpractico.model.DiaSemana
import com.example.primerpractico.viewmodel.RecetaViewModel

@Composable
fun PlanSemanalScreen(viewModel: RecetaViewModel) {

    val plan by viewModel.plan.collectAsState()
    val recetas by viewModel.recetas.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(DiaSemana.values()) { dia ->

            Column(modifier = Modifier.padding(8.dp)) {

                Text(text = dia.name)

                var expanded by remember { mutableStateOf(false) }

                Button(onClick = { expanded = true }) {
                    Text(plan[dia]?.nombre ?: "Seleccionar receta")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    recetas.forEach { receta ->
                        DropdownMenuItem(
                            text = { Text(receta.nombre) },
                            onClick = {
                                viewModel.asignarReceta(dia, receta)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}