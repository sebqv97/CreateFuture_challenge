package com.createfuture.home.data.characters

import com.createfuture.home.data.characters.dto.ApiCharacter
import com.createfuture.core.utils.safeApiCall
import com.createfuture.home.data.mapper.CharacterMapper
import com.createfuture.home.domain.model.GotCharacter
import com.createfuture.home.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: CharactersServiceApi,
    private val characterMapper: CharacterMapper
) : CharactersRepository {

    override suspend fun getCharacters(): Result<List<GotCharacter>> {
        return safeApiCall(
            apiCall = { api.getCharacters() },
            responseMapper = { it.map { characterMapper map it } })
    }
}
