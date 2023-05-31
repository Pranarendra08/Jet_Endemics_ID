package com.example.jetendemicsid

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.jetendemicsid.di.Injection
import com.example.jetendemicsid.model.DataHewan
import com.example.jetendemicsid.model.Hewan
import com.example.jetendemicsid.ui.ViewModelFactory
import com.example.jetendemicsid.ui.common.UiState
import com.example.jetendemicsid.ui.detail.DetailViewModel
import com.example.jetendemicsid.ui.theme.JetEndemicsIDTheme

@Composable
fun DetailScreen(
    hewanId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getHewanById(hewanId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    foto = data.foto,
                    nama = data.nama,
                    ilmiah = data.ilmiah,
                    deskripsi = data.deskripsi,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    foto: String,
    nama: String,
    ilmiah: String,
    deskripsi: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    )    {
        AsyncImage(
            model = foto,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()

        )
        Text(
            text = nama,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = ilmiah,
            fontStyle = FontStyle.Italic,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp)
        )
        Text(
            text = deskripsi,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    JetEndemicsIDTheme {
        DetailContent(
            foto = "",
            nama = "Harimau Sumatera",
            ilmiah = "Panthera tigris sumatrae",
            deskripsi = "Eksotisme harimau sumatra telah dikenal hingga seluruh dunia. Hewan endemik dengan tubuh terkecil dan warna kulit tergelap di antara jenis harimau lainnya ini, juga memiliki corak loreng hitam yang lebih rapat dan bila dilihat secara seksama motifnya menyerupai sidik jari manusia. Populasi harimau Sumatra diperkirakan hanya tinggal tersisa 400 ekor di alam bebas. Untuk mencegah kepunahan, Taman Nasional Kerinci Seblat, Kawasan Ekosistem Ulu Masen dan Leuser di Aceh dan Sumatra Utara menjadi pusat konservasi harimau Sumatra.",
            onBackClick = {}
        )
    }
}