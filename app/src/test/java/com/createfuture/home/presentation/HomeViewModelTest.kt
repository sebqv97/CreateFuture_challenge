package com.createfuture.home.presentation

import com.createfuture.home.domain.GetCharactersUseCase
import com.createfuture.home.domain.model.GotCharacter
import com.createfuture.home.utils.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.io.IOException


class HomeViewModelTest {

    @get:Rule
    val rule = CoroutineTestRule()

    private val getCharactersUseCase = mockk<GetCharactersUseCase>()
    private lateinit var subject: HomeViewModel

    @Before
    fun setUp() {
        subject = HomeViewModel(
            getCharactersUseCase,
            rule.appDispatchers
        )
    }

    @Test
    fun `check if first emitted element is loading state`() = runTest {
        val actual = subject.uiState.first()

        expectThat(actual).get { isLoading }.isTrue()
    }

    @Test
    fun `Given getCharacters throws an exception When loadDevices is called Then check error is emitted`() =
        runTest {
            val expectedException = IOException()
            coEvery { getCharactersUseCase.invoke() } throws expectedException

            subject.loadCharacters()

            backgroundScope.launch {
                expectThat(subject.uiState.last()) {
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
            coEvery { getCharactersUseCase.invoke() } returns Result.failure(expectedException)

            subject.loadCharacters()

            backgroundScope.launch {
                expectThat(subject.uiState.last()) {
                    get { error } isEqualTo expectedException
                    get { isLoading } isEqualTo false
                    get { characters } isEqualTo emptyList()
                }
            }
        }

    @Test
    fun `Given getCharactersUseCase returns success When loadDevices is called Then check characters are emitted`() =
        runTest {
            val expectedCharacters: List<GotCharacter> = listOf(mockk(), mockk(), mockk())
            coEvery { getCharactersUseCase.invoke() } returns Result.success(expectedCharacters)

            subject.loadCharacters()

            backgroundScope.launch {
                expectThat(subject.uiState.last()) {
                    get { error } isEqualTo null
                    get { isLoading } isEqualTo false
                    get { characters } isEqualTo expectedCharacters
                }
            }
        }

    @Test
    fun `Given searchQuery is blank when filterCharacters is called Then check all fetched characters are emitted`() =
        runTest {
            val expectedCharacters: List<GotCharacter> = listOf(mockk(), mockk(), mockk())
            coEvery { getCharactersUseCase.invoke() } returns Result.success(expectedCharacters)

            subject.loadCharacters()
            subject.filterCharacters("")

            backgroundScope.launch {
                expectThat(subject.uiState.last()) {
                    get { noEntryBasedOnSearchQuery } isEqualTo false
                    get { searchQuery } isEqualTo ""
                    get { characters } isEqualTo expectedCharacters
                }
            }
        }

    @Test
    fun `Given searchQuery when filterCharacters is called Then check correct characters are emitted`() =
        runTest {
            val retrievedCharacters: List<GotCharacter> = listOf(mockk {
                every { name } returns "Seb"
            }, mockk(relaxed = true), mockk(relaxed = true))
            coEvery { getCharactersUseCase.invoke() } returns Result.success(retrievedCharacters)

            subject.loadCharacters()
            subject.filterCharacters("s")

            backgroundScope.launch {
                expectThat(subject.uiState.last()) {
                    get { noEntryBasedOnSearchQuery } isEqualTo false
                    get { searchQuery } isEqualTo "s"
                    get { characters } hasSize 1
                }
            }
        }

    @Test
    fun `Given searchQuery is scribble when filterCharacters is called Then check correct characters are emitted`() =
        runTest {
            val retrievedCharacters: List<GotCharacter> = listOf(mockk {
                every { name } returns "Seb"
            }, mockk(relaxed = true), mockk(relaxed = true))
            coEvery { getCharactersUseCase.invoke() } returns Result.success(retrievedCharacters)

            subject.loadCharacters()
            subject.filterCharacters("ssfdsfasd")

            backgroundScope.launch {
                expectThat(subject.uiState.last()) {
                    get { noEntryBasedOnSearchQuery } isEqualTo true
                    get { searchQuery } isEqualTo "ssfdsfasd"
                }
            }
        }
}
