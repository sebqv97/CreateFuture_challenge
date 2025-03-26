package com.createfuture.login.presentation

import com.createfuture.home.utils.CoroutineTestRule
import com.createfuture.login.domain.usecase.HasUserLoggedInUseCase
import com.createfuture.login.domain.usecase.RetrieveAndSafeCharactersApiKeyUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val hasUserLoggedInUseCase = mockk<HasUserLoggedInUseCase>()
    private val retrieveAndSafeCharactersApiKeyUseCase =
        mockk<RetrieveAndSafeCharactersApiKeyUseCase>()
    private lateinit var subject: LoginViewModel

    @Test
    fun `Given hasUserLoggedIn returns true When viewModel inits Then check navigation emission is done`() =
        runTest {
            every { hasUserLoggedInUseCase.invoke() } returns true

            subject = LoginViewModel(
                retrieveAndSafeCharactersApiKeyUseCase,
                hasUserLoggedInUseCase,
                coroutineRule.appDispatchers
            )


            backgroundScope.launch {
                expectThat(subject.events.first()) isEqualTo LoginViewModel.LoginEvents.Navigation.NavigateToHome
            }
        }

    @Test
    fun `Given hasUserLoggedIn returns false When viewModel inits and onLogInPressed is called Then check navigation emission is done`() =
        runTest {
            every { hasUserLoggedInUseCase.invoke() } returns false

            subject = LoginViewModel(
                retrieveAndSafeCharactersApiKeyUseCase,
                hasUserLoggedInUseCase,
                coroutineRule.appDispatchers
            ).also { it.onLoginPressed() }

            backgroundScope.launch {
                expectThat(subject.events.first()) isEqualTo LoginViewModel.LoginEvents.Navigation.NavigateToHome
            }
        }
}
