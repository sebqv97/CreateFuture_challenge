package com.createfuture.home.domain.repository

interface SecureRepository {

    fun saveToken(key: String, token: String)

    fun getToken(key: String): String?

    fun clearToken(key: String)
}