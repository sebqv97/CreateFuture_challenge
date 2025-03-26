package com.createfuture.home.domain.usecase

import com.createfuture.home.domain.repository.SecureRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class RetrieveAndSafeCharactersApiKeyUseCase @Inject constructor(
    private val secureRepository: SecureRepository
) {
    suspend operator fun invoke() {
        //mimic an api call to the server to retrieve the AUTH key needed for characters API
        // it is recommended not to keep the API key in the APK as it can be retrieved by attackers
        // instead, we will simulate an API call and as soon as it retrieves, we will save it in Encrypted shared Prefs
        delay(1)
        secureRepository.saveToken(AUTH_TOKEN_KEY, "754t!si@glcE2qmOFEcN")
    }

    private companion object {
        const val AUTH_TOKEN_KEY = "auth token key"
    }
}