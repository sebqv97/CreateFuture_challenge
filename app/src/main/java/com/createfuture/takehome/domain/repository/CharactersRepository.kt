package com.createfuture.takehome.domain.repository

import com.createfuture.takehome.data.characters.dto.ApiCharacter

interface CharactersRepository {
    suspend fun getCharacters(): Result<List<ApiCharacter>>
}