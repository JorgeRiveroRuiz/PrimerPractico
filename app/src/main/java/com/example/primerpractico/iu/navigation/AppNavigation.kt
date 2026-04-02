package com.example.recetas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.primerpractico.iu.screens.ListaComprasScreen
import com.example.primerpractico.iu.screens.PlanSemanalScreen
import com.example.primerpractico.recetas.ui.screens.CrearRecetaScreen
import com.example.primerpractico.recetas.ui.screens.ListaRecetasScreen
import com.example.primerpractico.viewmodel.RecetaViewModel


@Composable
fun AppNavigation(viewModel: RecetaViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista"
    ) {

        composable("lista") {
            ListaRecetasScreen(
                viewModel = viewModel,
                onNavigateToCrear = {
                    navController.navigate("crear")
                }
            )
        }

        composable("crear") {
            CrearRecetaScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("plan") {
            PlanSemanalScreen(viewModel)
        }

        composable("compras") {
            ListaComprasScreen(viewModel)
        }
    }
}