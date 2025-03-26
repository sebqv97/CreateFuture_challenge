package com.createfuture.login.domain.usecase
import com.createfuture.login.domain.LoginRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RetrieveAndSafeCharactersApiKeyUseCaseTest {

    private val loginRepository = mockk<LoginRepository>()
    private lateinit var subject: RetrieveAndSafeCharactersApiKeyUseCase

    @Before
    fun setup() {
        subject = RetrieveAndSafeCharactersApiKeyUseCase(loginRepository)
    }

  @Test
  fun `check useCase when invoked calls loginRepository`() = runTest{
      coJustRun { loginRepository.fetchAuthToken() }

      subject.invoke()

      coVerify { loginRepository.fetchAuthToken() }
  }
}