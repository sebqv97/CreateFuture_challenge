package com.createfuture.home.presentation

import com.createfuture.home.domain.model.GotCharacter

data class HomeUiState(
    val characters: List<GotCharacter> = emptyList(),
    val searchQuery: String = "",
    val noEntryBasedOnSearchQuery:Boolean = false,
    val isLoading: Boolean = false,
    val error:Throwable? = null
)