package com.example.tp1_applicationfacture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Facture(navController: NavHostController) {
    var quantite by remember { mutableStateOf("") }
    var prixUnitaire by remember { mutableStateOf("") }
    var tauxTVA by remember { mutableStateOf("") }
    var clientFidele by remember { mutableStateOf(false) }
    var remise by remember { mutableStateOf("") }

    var messageErreur by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = quantite,
            onValueChange = { quantite = it },
            label = { Text("Quantité") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = prixUnitaire,
            onValueChange = { prixUnitaire = it },
            label = { Text("Prix unitaire") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tauxTVA,
            onValueChange = { tauxTVA = it },
            label = { Text("Taux de TVA (%)") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = clientFidele,
                onCheckedChange = { clientFidele = it }
            )
            Text("Client fidèle")
        }

        if (clientFidele) {
            OutlinedTextField(
                value = remise,
                onValueChange = { remise = it },
                label = { Text("Remise (%)") }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            try {
                val quantiteInt = quantite.toInt()
                val prixUnitaireDouble = prixUnitaire.toDouble()
                val tauxTVADouble = tauxTVA.toDouble()
                val remiseDouble = if (clientFidele && remise.isNotEmpty()) remise.toDouble() else 0.0

                val totalHT = quantiteInt * prixUnitaireDouble
                val montantRemise = totalHT * remiseDouble / 100
                val totalHTAvecRemise = totalHT - montantRemise
                val montantTVA = totalHTAvecRemise * tauxTVADouble / 100
                val totalTTC = totalHTAvecRemise + montantTVA

                messageErreur = ""
                val totalHTStr = totalHT.toString()
                navController.navigate("resultatTTC/$totalTTC")

            } catch (e: Exception) {
                messageErreur = "Veuillez entrer des valeurs valides."
            }
        }) {
            Text("Calculer le TTC")
        }

        Button(onClick = {
            quantite = ""
            prixUnitaire = ""
            tauxTVA = ""
            clientFidele = false
            remise = ""
            messageErreur = ""
        }) {
            Text("Remise à zéro")
        }

        if (messageErreur.isNotEmpty()) {
            Text(messageErreur, color = Color.Red)
        }
    }
}