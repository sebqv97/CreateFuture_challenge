package com.createfuture.takehome.domain.usecase
import com.createfuture.takehome.domain.repository.SecureRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RetrieveAndSafeCharactersApiKeyUseCaseTest {

    private val secureRepository = mockk<SecureRepository>()
    private lateinit var subject: RetrieveAndSafeCharactersApiKeyUseCase

    @Before
    fun setup() {
        subject = RetrieveAndSafeCharactersApiKeyUseCase(secureRepository)
    }

    @Test
    fun `When useCase invoked, then check token is saved`() = runTest{
        val expectedToken = "754t!si@glcE2qmOFEcN"
        justRun { secureRepository.saveToken(AUTH_TOKEN_KEY, expectedToken) }

        subject.invoke()

        verify { secureRepository.saveToken(AUTH_TOKEN_KEY, expectedToken) }
    }

    private companion object{
        const val AUTH_TOKEN_KEY = "auth token key"
    }
}