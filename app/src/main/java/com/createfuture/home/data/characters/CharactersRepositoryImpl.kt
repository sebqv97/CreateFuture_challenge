package com.createfuture.home.data.characters

import com.createfuture.home.data.characters.dto.ApiCharacter
import com.createfuture.home.core.utils.safeApiCall
import com.createfuture.home.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val api: CharactersServiceApi): CharactersRepository {

   override suspend fun getCharacters():Result<List<ApiCharacter>>{
        return safeApiCall { api.getCharacters() }
    }
}
