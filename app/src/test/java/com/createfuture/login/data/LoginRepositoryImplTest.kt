package com.createfuture.login.data

import com.createfuture.home.domain.repository.SecureRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class LoginRepositoryImplTest {

    private val secureRepository = mockk<SecureRepository>()
    private lateinit var subject: LoginRepositoryImpl

    @Before
    fun setup() {
        subject = LoginRepositoryImpl(secureRepository)
    }

    @Test
    fun `Given auth token is already saved When fetchToken is called, then check saveToken is not called`() =
        runTest {
            every { secureRepository.getToken("auth token key") } returns "token"

            subject.fetchAuthToken()

            verify(exactly = 0) { secureRepository.saveToken(any(), any()) }

        }

    @Test
    fun `Given auth token is not on device When fetchTonken is called, then check saveToken is called`() =
        runTest {
            justRun { secureRepository.saveToken("auth token key", "754t!si@glcE2qmOFEcN") }
            every { secureRepository.getToken("auth token key") } returns null

            subject.fetchAuthToken()

            verify(exactly = 1) {
                secureRepository.saveToken(
                    "auth token key",
                    "754t!si@glcE2qmOFEcN"
                )
            }

        }

    @Test
    fun `Given token is not saved When isAuthTokenPresent is called Then check we get false`() =
        runTest {
            every { secureRepository.getToken("auth token key") } returns null

            val actual = subject.isAuthTokenPresent()

            expectThat(actual).isFalse()
        }

    @Test
    fun `Given token is saved When isAuthTokenPresent is called Then check we get true`() =
        runTest {
            every { secureRepository.getToken("auth token key") } returns "token"

            val actual = subject.isAuthTokenPresent()

            expectThat(actual).isTrue()
        }

}