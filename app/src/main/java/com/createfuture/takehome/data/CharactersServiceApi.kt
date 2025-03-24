package com.createfuture.takehome.data

import com.createfuture.takehome.data.dto.ApiCharacter
import retrofit2.Response
import retrofit2.http.GET

interface CharactersServiceApi {
    @GET("/characters")
    // @Header("Authorization") token: String use interceptor
    suspend fun getCharacters(): Response<List<ApiCharacter>>
}
