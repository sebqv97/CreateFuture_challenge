package com.createfuture.takehome.presentation

import com.createfuture.takehome.data.characters.dto.ApiCharacter

data class HomeUiState(
    val characters: List<ApiCharacter> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error:Throwable? = null
)