package com.createfuture.home.presentation

import com.createfuture.home.data.characters.dto.ApiCharacter
import com.createfuture.home.domain.GetCharactersUseCase
import com.createfuture.home.domain.usecase.RetrieveAndSafeCharactersApiKeyUseCase
import com.createfuture.home.utils.CoroutineTestRule
import com.createfuture.home.utils.test
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val rule = CoroutineTestRule(UnconfinedTestDispatcher())

    private val getCharactersUseCase = mockk<GetCharactersUseCase>()
    private val retrieveAndSafeCharactersApiKeyUseCase =
        mockk<RetrieveAndSafeCharactersApiKeyUseCase>()
    private lateinit var subject: HomeViewModel

    @Before
    fun setUp() {
        subject = HomeViewModel(
            getCharactersUseCase,
            retrieveAndSafeCharactersApiKeyUseCase,
            rule.testDispatcher
        )
    }

    @Test
    fun `check if first emitted element is loading state`() = runTest {
        val actual = subject.uiState.first()

        expectThat(actual).get { isLoading }.isTrue()
    }

    @Test
    fun `Given retrieveKeyUseCase throws an exception When loadDevices is called Then check error is emitted`() =
        runTest {
            val expectedException = IOException()
            coEvery { retrieveAndSafeCharactersApiKeyUseCase.invoke() } throws expectedException
            coEvery { getCharactersUseCase.invoke() } returns Result.success(emptyList())

            subject.loadCharacters()

            subject.uiState.test {
                advanceUntilIdle()
                expectThat(it.last()) {
                    get { error } isEqualTo expectedException
                    get { isLoading } isEqualTo false
                    get { characters } isEqualTo emptyList()
                }
            }
        }

    @Test
    fun `Given getCharactersUseCase returns failure When loadDevices is called Then check error is emitted`() =
        runTest {
            val expectedException = IOException()
            coJustRun { retrieveAndSafeCharactersApiKeyUseCase.invoke() }
            coEvery { getCharactersUseCase.invoke() } returns Result.failure(expectedException)

            subject.loadCharacters()

            subject.uiState.test {
                advanceUntilIdle()
                expectThat(it.last()) {
                    get { error } isEqualTo expectedException
                    get { isLoading } isEqualTo false
                    get { characters } isEqualTo emptyList()
                }
            }
        }

    @Test
    fun `Given getCharactersUseCase returns success When loadDevices is called Then check characters are emitted`() =
        runTest {
            val expectedCharacters: List<ApiCharacter> = listOf(mockk(), mockk(), mockk())
            coJustRun { retrieveAndSafeCharactersApiKeyUseCase.invoke() }
            coEvery { getCharactersUseCase.invoke() } returns Result.success(expectedCharacters)

            subject.loadCharacters()

            subject.uiState.test {
                advanceUntilIdle()
                expectThat(it.last()) {
                    get { error } isEqualTo null
                    get { isLoading } isEqualTo false
                    get { characters } isEqualTo expectedCharacters
                }
            }
        }

    @Test
    fun `Given searchQuery is blank when filterCharacters is called Then check all fetched characters are emitted`() =
        runTest {
            val expectedCharacters: List<ApiCharacter> = listOf(mockk(), mockk(), mockk())
            coJustRun { retrieveAndSafeCharactersApiKeyUseCase.invoke() }
            coEvery { getCharactersUseCase.invoke() } returns Result.success(expectedCharacters)

            subject.loadCharacters()
            subject.filterCharacters("")

            subject.uiState.test {
                advanceUntilIdle()
                expectThat(it.last()) {
                    get { noEntryBasedOnSearchQuery } isEqualTo false
                    get { searchQuery } isEqualTo ""
                    get { characters } isEqualTo expectedCharacters
                }
            }
        }

    @Test
    fun `Given searchQuery when filterCharacters is called Then check correct characters are emitted`() =
        runTest {
            val retrievedCharacters: List<ApiCharacter> = listOf(mockk {
                every { name } returns "Seb"
            }, mockk(relaxed = true), mockk(relaxed = true))
            coJustRun { retrieveAndSafeCharactersApiKeyUseCase.invoke() }
            coEvery { getCharactersUseCase.invoke() } returns Result.success(retrievedCharacters)

            subject.loadCharacters()
            subject.filterCharacters("s")

            subject.uiState.test {
                advanceUntilIdle()
                expectThat(it.last()) {
                    get { noEntryBasedOnSearchQuery } isEqualTo false
                    get { searchQuery } isEqualTo "s"
                    get { characters } hasSize 1
                }
            }
        }

    @Test
    fun `Given searchQuery is scribble when filterCharacters is called Then check correct characters are emitted`() =
        runTest {
            val retrievedCharacters: List<ApiCharacter> = listOf(mockk {
                every { name } returns "Seb"
            }, mockk(relaxed = true), mockk(relaxed = true))
            coJustRun { retrieveAndSafeCharactersApiKeyUseCase.invoke() }
            coEvery { getCharactersUseCase.invoke() } returns Result.success(retrievedCharacters)

            subject.loadCharacters()
            subject.filterCharacters("ssfdsfasd")

            subject.uiState.test {
                advanceUntilIdle()
                expectThat(it.last()) {
                    get { noEntryBasedOnSearchQuery } isEqualTo true
                    get { searchQuery } isEqualTo "ssfdsfasd"
                }
            }
        }
}
