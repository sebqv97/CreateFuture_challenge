package com.createfuture.takehome.data.characters

import com.createfuture.takehome.data.characters.dto.ApiCharacter
import com.createfuture.takehome.core.utils.safeApiCall
import com.createfuture.takehome.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val api: CharactersServiceApi): CharactersRepository {

   override suspend fun getCharacters():Result<List<ApiCharacter>>{
        return safeApiCall { api.getCharacters() }
    }
}