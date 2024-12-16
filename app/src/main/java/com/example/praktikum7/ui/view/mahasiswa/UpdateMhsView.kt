package com.example.praktikum7.ui.view.mahasiswa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum7.ui.viewmodel.PenyediaViewModel
import com.example.praktikum7.ui.viewmodel.UpdateMhsViewModel

@Composable
fun UpdateMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    viewModel: UpdateMhsViewModel = viewModel(factory = PenyediaViewModel.Factory)
            Inisialisasi ViewModel
) {
    val uiState = viewModel.updateUiState //Ambil Ui state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect trigged")
        uiState.snackBarMessage?.let { message ->
            println("Snackbar message received: $message")
            snackbarHostState.showSnackbar(
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
            )
        }
    }

    Scaffold(
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        )
    ) {  }
}