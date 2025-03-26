package com.createfuture.home.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DisplayNoEntries(searchQuery: String){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(textAlign = TextAlign.Center, text = "No entries based on `$searchQuery`", color = Color.White, modifier = Modifier.padding(16.dp))
    }
}
