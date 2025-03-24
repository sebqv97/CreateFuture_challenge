package com.createfuture.takehome.domain.repository

interface SecureRepository {

    fun saveToken(key: String, token: String)

    fun getToken(key: String): String?

    fun clearToken(key: String)
}