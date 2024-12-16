package com.example.praktikum7.ui.theme.view.mahasiswa

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum7.ui.theme.costumwidget.TopAppBar
import com.example.praktikum7.ui.theme.viewmodel.DetailMhsViewModel
import com.example.praktikum7.ui.theme.viewmodel.PenyediaViewModel

@Composable
fun DetailMhsView(
    modifier: Modifier = Modifier,
    viewModel: DetailMhsViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
){
    Scaffold(
        topBar = {
            TopAppBar(
                judul = " Detail Mahasiswa",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton =
    ) {}
}