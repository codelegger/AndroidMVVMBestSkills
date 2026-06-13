package com.codelegger.androidmvvmbestskills.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codelegger.androidmvvmbestskills.domain.model.Sample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Android MVVM Best Skills") }) },
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            when {
                uiState.isLoading && uiState.samples.isEmpty() -> CircularProgressIndicator()
                uiState.errorMessage != null && uiState.samples.isEmpty() ->
                    Text(text = uiState.errorMessage ?: "Something went wrong")
                else -> SampleList(samples = uiState.samples)
            }
        }
    }
}

@Composable
private fun SampleList(samples: List<Sample>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = samples, key = { it.id }) { sample ->
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = sample.title, style = MaterialTheme.typography.titleMedium)
                Text(text = sample.body, style = MaterialTheme.typography.bodyMedium)
            }
            HorizontalDivider()
        }
    }
}
