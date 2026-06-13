package com.codelegger.androidmvvmbestskills.ui.security

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
import com.codelegger.androidmvvmbestskills.ui.theme.AppTheme

/** Placeholder hub screen linking to the individual security demos. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityDashboardScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("Security Dashboard") }) },
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center,
        ) {
            Text("Security Dashboard — coming soon")
        }
    }
}

@Preview
@Composable
private fun SecurityDashboardScreenPreview() {
    AppTheme {
        SecurityDashboardScreen()
    }
}
