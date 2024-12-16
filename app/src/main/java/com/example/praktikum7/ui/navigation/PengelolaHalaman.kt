package com.example.praktikum7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praktikum7.ui.view.mahasiswa.DestinasiInsert
import com.example.praktikum7.ui.view.mahasiswa.DetailMhsView
import com.example.praktikum7.ui.view.mahasiswa.InsertMhsView
import com.example.praktikum7.ui.view.mahasiswa.UpdateMhsView

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
                },
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)

            nim?.let { nim ->
                DetailMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMhsView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}