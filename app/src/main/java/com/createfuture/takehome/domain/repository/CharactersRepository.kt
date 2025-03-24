package com.createfuture.takehome.domain.repository

import com.createfuture.takehome.data.dto.ApiCharacter

interface CharactersRepository {
    fun getCharacters(): Result<ApiCharacter>
}