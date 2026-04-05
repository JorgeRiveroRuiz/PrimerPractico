package com.example.primerpractico.iu.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.primerpractico.viewmodel.RecetaViewModel


@Composable
fun ListaComprasScreen(viewModel: RecetaViewModel, ) {

    val lista = viewModel.listaCompras()
    val comprados by viewModel.comprados.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(lista.keys.toList()) { ingrediente ->

            val estaComprado = comprados.contains(ingrediente)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(text = ingrediente)

                    lista[ingrediente]?.forEach { cantidad ->
                        Text(text = "- $cantidad")
                    }
                }

                Checkbox(
                    checked = estaComprado,
                    onCheckedChange = {
                        viewModel.toggleComprado(ingrediente)
                    }
                )
            }
        }
    }
}