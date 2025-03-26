package com.createfuture.login.data

import com.createfuture.home.domain.repository.SecureRepository
import com.createfuture.login.domain.LoginRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val secureRepository: SecureRepository
) : LoginRepository {
    override suspend fun fetchAuthToken() {
        // considering the auth token a session token, meaning that as long as the use logged in once,
        // the token will be saved into encrypted shared prefs and fetched from local
        if (isAuthTokenPresent()) return
        //mimic an api call to the server to retrieve the AUTH key needed for characters API
        // it is recommended not to keep the API key in the APK as it can be retrieved by attackers
        // instead, we will simulate an API call and as soon as it retrieves, we will save it in Encrypted shared Prefs
        delay(1)
        secureRepository.saveToken(AUTH_TOKEN_KEY, "754t!si@glcE2qmOFEcN")

    }

    override fun isAuthTokenPresent(): Boolean {
        return secureRepository.getToken(AUTH_TOKEN_KEY) != null
    }

    private companion object {
        const val AUTH_TOKEN_KEY = "auth token key"
    }
}
