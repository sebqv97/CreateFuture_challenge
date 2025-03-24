package com.createfuture.takehome.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(onTextChanged: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .alpha(0.5f),
        value = searchQuery,
        onValueChange = {
            searchQuery = it
            onTextChanged(it)
        },
        label = { Text("Search") })
}
