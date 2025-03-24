package com.createfuture.takehome.core.interceptor

import com.createfuture.takehome.data.characters.RequiresCharactersAuthHeader
import com.createfuture.takehome.domain.repository.SecureRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorisationHeaderInterceptor @Inject constructor(
    private val secureRepository: SecureRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.tag(retrofit2.Invocation::class.java)?.method()

        if (method?.getAnnotation(RequiresCharactersAuthHeader::class.java) != null) {
            secureRepository.getToken(AUTH_TOKEN_KEY)?.let {
                val newRequest = request.newBuilder()
                    .addHeader(AUTHORIZATION_HEADER, String.format(AUTH_HEADER_TEMPLATE, it))
                    .build()
                return chain.proceed(newRequest)
            }
        }

        return chain.proceed(request)
    }

    private companion object {
        const val AUTH_HEADER_TEMPLATE = "Bearer %s"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val AUTH_TOKEN_KEY = "auth token key"
    }
}