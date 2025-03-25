package com.createfuture.takehome.core

import android.content.Context
import com.createfuture.takehome.core.interceptor.AuthorisationHeaderInterceptor
import com.createfuture.takehome.core.utils.AppDispatchers
import com.createfuture.takehome.data.characters.CharactersRepositoryImpl
import com.createfuture.takehome.data.characters.CharactersServiceApi
import com.createfuture.takehome.data.encryption.EncryptedSharedPrefsRepository
import com.createfuture.takehome.domain.repository.CharactersRepository
import com.createfuture.takehome.domain.repository.SecureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(authorisationHeaderInterceptor: AuthorisationHeaderInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authorisationHeaderInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideCharactersServiceApi(retrofit: Retrofit): CharactersServiceApi =
        retrofit.create(CharactersServiceApi::class.java)

    @Singleton
    @Provides
    fun provideCharactersRepository(api: CharactersServiceApi): CharactersRepository =
        CharactersRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideSecureRepository(@ApplicationContext context: Context): SecureRepository =
        EncryptedSharedPrefsRepository(context)

    @Singleton
    @Provides
    fun provideAppDispatchers(): AppDispatchers = AppDispatchers()


    private companion object {
        const val BASE_URL = "https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com"
    }
}