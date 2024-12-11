package com.example.praktikum7.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktikum7.ui.theme.view.mahasiswa.DestinasiInsert
import com.example.praktikum7.ui.theme.view.mahasiswa.InsertMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiInsert.route
    ) {
        composable(
            route = DestinasiInsert.route
        ) {
            InsertMhsView(
                onBack = {
                    // Aksi untuk kembali
                    navController.popBackStack()
                },
                onNavigate = {
                    // Aksi untuk navigasi ke halaman lain
                    navController.navigate("destinasiLain") // Ganti dengan rute yang sesuai
                }
            )
        }
    }
}