package com.createfuture.login.domain.usecase

import com.createfuture.login.domain.LoginRepository
import javax.inject.Inject

class HasUserLoggedInUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(): Boolean = loginRepository.isAuthTokenPresent()
}