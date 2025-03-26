package com.createfuture.login.domain.usecase

import com.createfuture.login.domain.LoginRepository
import javax.inject.Inject

class RetrieveAndSafeCharactersApiKeyUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke() {
      loginRepository.fetchAuthToken()
    }
}