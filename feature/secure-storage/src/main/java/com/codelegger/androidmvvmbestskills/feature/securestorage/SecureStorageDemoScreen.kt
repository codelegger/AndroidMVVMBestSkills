package com.codelegger.androidmvvmbestskills.feature.securestorage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/** Placeholder screen for the encrypted-storage demo. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecureStorageDemoScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("Secure Storage Demo") }) },
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center,
        ) {
            Text("Secure Storage Demo — coming soon")
        }
    }
}

@Preview
@Composable
private fun SecureStorageDemoScreenPreview() {
    SecureStorageDemoScreen()
}
