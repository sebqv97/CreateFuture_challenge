package com.createfuture.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.createfuture.core.utils.AppDispatchers
import com.createfuture.home.data.characters.dto.ApiCharacter
import com.createfuture.home.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private lateinit var retrievedList: List<ApiCharacter>

    fun loadCharacters() {
        viewModelScope.launch {
            val response = withContext(appDispatchers.IO) {
                try {
                    getCharactersUseCase()
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }

            response.fold(onSuccess = { listOfCharacters ->
                retrievedList = listOfCharacters
                _uiState.update { state ->
                    state.copy(characters = listOfCharacters, isLoading = false)
                }
            }, onFailure = { error ->
                _uiState.update { state ->
                    state.copy(error = error, isLoading = false)
                }
            })
        }
    }

    fun filterCharacters(query: String) {
        val filteredCharacters =
            if (query.isBlank()) {
                retrievedList
            } else {
                retrievedList.filter { it.name.startsWith(query, ignoreCase = true) }
            }
        _uiState.update { state ->
            state.copy(
                characters = filteredCharacters,
                noEntryBasedOnSearchQuery = filteredCharacters.isEmpty(),
                searchQuery = query
            )
        }
    }
}
