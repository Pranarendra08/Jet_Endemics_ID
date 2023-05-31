package com.example.jetendemicsid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetendemicsid.di.Injection
import com.example.jetendemicsid.model.DataHewan
import com.example.jetendemicsid.model.Hewan
import com.example.jetendemicsid.ui.ViewModelFactory
import com.example.jetendemicsid.ui.common.UiState
import com.example.jetendemicsid.ui.home.HomeViewModel
import com.example.jetendemicsid.ui.theme.JetEndemicsIDTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllHewan()
            }
            is UiState.Success -> {
                Column {
                    Box {
                        SearchBar(
                            query = query,
                            onQueryChange = viewModel::search,
                            modifier = Modifier.background(MaterialTheme.colors.primary)
                        )
                    }
                    HomeContent(
                        listHewan = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
            is UiState.Error -> {}
        }

    }
}

@Composable
fun HomeContent(
    listHewan: List<Hewan>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(listHewan) { data ->
            HewanListItem(
                nama = data.nama,
                ilmiah = data.ilmiah,
                foto = data.foto,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.search_hewan))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}
