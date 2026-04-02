package com.example.primerpractico.viewmodel

import com.example.primerpractico.model.DiaSemana
import com.example.primerpractico.model.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecetaViewModel {


    // ---------- RECETAS ----------
    private val _recetas = MutableStateFlow<List<Receta>>(emptyList())
    val recetas: StateFlow<List<Receta>> = _recetas

    fun agregarReceta(receta: Receta) {
        _recetas.value = _recetas.value + receta
    }

    // ---------- BUSQUEDA ----------
    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda

    fun actualizarBusqueda(texto: String) {
        _busqueda.value = texto
    }

    fun recetasFiltradas(): List<Receta> {
        val texto = _busqueda.value.lowercase()

        return _recetas.value.filter {
            it.nombre.lowercase().contains(texto)
        }
    }

    // ---------- PLAN SEMANAL ----------
    private val _plan: MutableStateFlow<Map<DiaSemana, Receta?>> =
        MutableStateFlow(
            DiaSemana.values().associateWith<DiaSemana, Receta?> { null }
        )

    val plan: StateFlow<Map<DiaSemana, Receta?>> = _plan

    fun asignarReceta(dia: DiaSemana, receta: Receta) {
        _plan.value = _plan.value.toMutableMap().apply {
            this[dia] = receta
        }
    }

    // ---------- LISTA DE COMPRAS ----------
    private val _comprados = MutableStateFlow<Set<String>>(emptySet())
    val comprados: StateFlow<Set<String>> = _comprados

    fun toggleComprado(nombreIngrediente: String) {
        _comprados.value =
            if (_comprados.value.contains(nombreIngrediente)) {
                _comprados.value - nombreIngrediente
            } else {
                _comprados.value + nombreIngrediente
            }
    }

    fun listaCompras(): Map<String, List<String>> {
        val recetasPlan = _plan.value.values.filterNotNull()

        return recetasPlan
            .flatMap { it.ingredientes }
            .groupBy { it.nombre }
            .mapValues { entry ->
                entry.value.map { it.cantidad }
            }
    }

}