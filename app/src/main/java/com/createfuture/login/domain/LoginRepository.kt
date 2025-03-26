package com.createfuture.login.domain

interface LoginRepository  {
    suspend fun fetchAuthToken()
    fun isAuthTokenPresent(): Boolean
}