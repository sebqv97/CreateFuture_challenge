package com.createfuture.takehome.di

import com.createfuture.takehome.data.CharactersServiceApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val retrofit: Retrofit
    val charactersServiceApi: CharactersServiceApi
}

class AppModuleImpl() : AppModule {
    override val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())
            ).client(OkHttpClient.Builder().build()).build()
    }
    override val charactersServiceApi: CharactersServiceApi by lazy {
        retrofit.create(CharactersServiceApi::class.java)
    }


    private companion object {
        const val BASE_URL = "https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com"
    }
}