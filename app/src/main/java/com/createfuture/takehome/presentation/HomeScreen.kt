package com.createfuture.takehome.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.createfuture.takehome.R
import com.createfuture.takehome.presentation.composable.DisplayNoEntries
import com.createfuture.takehome.presentation.composable.RenderCharacters
import com.createfuture.takehome.presentation.composable.SearchBar
import com.createfuture.takehome.presentation.composable.ShowError
import com.createfuture.takehome.presentation.composable.ShowLoadingSpinner

@Composable
fun HomeScreen(viewModel: HomeViewModel, modifier: Modifier) {
    LaunchedEffect(true) {
        viewModel.loadCharacters()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.img_characters),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        val state by viewModel.uiState.collectAsState(initial = HomeUiState(isLoading = true))

        SearchBar(onTextChanged = { viewModel.filterCharacters(it) })

        when {
            state.noEntryBasedOnSearchQuery -> DisplayNoEntries(state.searchQuery)
            state.isLoading -> ShowLoadingSpinner()
            state.error != null -> state.error?.let { ShowError(it) }
            state.characters.isNotEmpty() -> RenderCharacters(state.characters)
        }
    }
}

