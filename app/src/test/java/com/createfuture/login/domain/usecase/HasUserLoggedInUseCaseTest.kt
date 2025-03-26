package com.createfuture.login.domain.usecase

import com.createfuture.login.domain.LoginRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HasUserLoggedInUseCaseTest {
    private val loginRepository = mockk<LoginRepository>()
    private lateinit var subject: HasUserLoggedInUseCase

    @Before
    fun setup() {
        subject = HasUserLoggedInUseCase(loginRepository)
    }

    @Test
    fun `check useCase when invoked calls loginRepository`() = runTest {
        every{ loginRepository.isAuthTokenPresent() } returns false
        subject.invoke()

        coVerify { loginRepository.isAuthTokenPresent() }
    }
}