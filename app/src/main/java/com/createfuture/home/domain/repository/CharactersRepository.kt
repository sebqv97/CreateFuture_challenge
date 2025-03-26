package com.createfuture.home.domain.repository

import com.createfuture.home.data.characters.dto.ApiCharacter

interface CharactersRepository {
    suspend fun getCharacters(): Result<List<ApiCharacter>>
}