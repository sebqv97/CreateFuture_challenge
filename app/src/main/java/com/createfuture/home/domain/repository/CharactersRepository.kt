package com.createfuture.home.domain.repository

import com.createfuture.home.domain.model.GotCharacter

interface CharactersRepository {
    suspend fun getCharacters(): Result<List<GotCharacter>>
}