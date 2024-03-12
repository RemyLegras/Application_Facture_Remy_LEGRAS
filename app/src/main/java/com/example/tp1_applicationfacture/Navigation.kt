package com.example.tp1_applicationfacture

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable(route = "Login"){
            Login(navController)
        }
        composable(route = "Facture"){
            Facture(navController)
        }
        composable("resultatTTC/{montantTTC}", arguments = listOf(navArgument("montantTTC") { type = NavType.StringType })) { backStackEntry ->
            ResultatTTC(navController, backStackEntry.arguments?.getString("montantTTC"))
        }
    }
}
