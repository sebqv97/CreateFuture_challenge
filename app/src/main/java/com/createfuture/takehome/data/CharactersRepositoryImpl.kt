package com.createfuture.takehome.data

import com.createfuture.takehome.data.dto.ApiCharacter
import com.createfuture.takehome.utils.safeApiCall

class CharactersRepositoryImpl(private val api: CharactersServiceApi) {

    fun getCharacters():Result<ApiCharacter>{
        return safeApiCall { api.getCharacters() }
    }
}