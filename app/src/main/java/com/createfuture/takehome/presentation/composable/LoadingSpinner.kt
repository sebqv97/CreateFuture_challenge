package com.createfuture.takehome.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingSpinner() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Loading...", Modifier.padding(16.dp))
            CircularProgressIndicator(Modifier.padding(16.dp))
        }
}