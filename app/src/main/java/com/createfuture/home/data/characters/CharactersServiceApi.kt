package com.createfuture.home.data.characters

import com.createfuture.home.data.characters.dto.ApiCharacter
import retrofit2.Response
import retrofit2.http.GET

interface CharactersServiceApi {
    @GET("/characters")
    @RequiresCharactersAuthHeader
    // @Header("Authorization") token: String use interceptor
    suspend fun getCharacters(): Response<List<ApiCharacter>>
}
