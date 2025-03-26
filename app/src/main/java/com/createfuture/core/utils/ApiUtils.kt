package com.createfuture.core.utils

import retrofit2.Response

suspend fun <T,R> safeApiCall(apiCall: suspend () -> Response<T>, responseMapper:(T) -> R): Result<R> {
    return try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.success(responseMapper(body))
            } else {
                Result.failure((Exception("Response body is null")))
            }
        } else {
            Result.failure(Exception("API call failed with code: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
