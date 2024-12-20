package com.example.praktikum7.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum7.data.entity.Mahasiswa
import com.example.praktikum7.repository.RepositoryMhs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs,

    ) : ViewModel() {
        private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])
            val  detailUiState: StateFlow<DetailUiState> = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .map{
                    DetailUiState(
                        detailUiEvent = it.toDetailUiEvent(),
                        isLoading = false,
                    )
                }
                .onStart {
                    emit(DetailUiState(isLoading = true))
                    delay(600)
                }
                .catch {
                    emit(
                        DetailUiState(
                            isLoading = false,
                            isError = true,
                            errorMessage = it.message ?: "Terjadi kesalahan",
                        )
                    )
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(2000),
                    initialValue = DetailUiState(
                        isLoading = true,
                    ),
                )

    fun deleteMhs() {
        detailUiState.value.detailUiEvent.toMahasiswaEntity().let {
            viewModelScope.launch {
                repositoryMhs.deleteMhs(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MahasiswaEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MahasiswaEvent()

}

//Data class untuk menampung data yang akan di tampilkan di Ui

//memindahkan data dari entity ke ui
fun Mahasiswa.toDetailUiEvent() : MahasiswaEvent {
    return MahasiswaEvent(
        nim = nim,
        nama = nama,
        jeniskelamin = jeniskelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan,
    )
}

