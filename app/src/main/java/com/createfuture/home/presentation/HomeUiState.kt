package com.createfuture.home.presentation

import com.createfuture.home.data.characters.dto.ApiCharacter

data class HomeUiState(
    val characters: List<ApiCharacter> = emptyList(),
    val searchQuery: String = "",
    val noEntryBasedOnSearchQuery:Boolean = false,
    val isLoading: Boolean = false,
    val error:Throwable? = null
)