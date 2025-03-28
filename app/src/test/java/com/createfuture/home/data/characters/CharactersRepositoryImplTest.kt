package com.createfuture.home.data.characters

import com.createfuture.home.data.mapper.CharacterMapper
import com.createfuture.home.domain.repository.CharactersRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import strikt.api.expect
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import java.io.IOException

class CharactersRepositoryImplTest {

    private val charactersApi = mockk<CharactersServiceApi>()
    private val charactersMapper = mockk<CharacterMapper>()
    private lateinit var subject: CharactersRepository

    @Before
    fun setUp() {
        subject = CharactersRepositoryImpl(charactersApi, charactersMapper)
    }

    @Test
    fun `Given charactersApi throws error When calling getCharacters Then check we get failure`() =
        runTest {
            val expectedException = IOException("Server is down")
            coEvery { charactersApi.getCharacters() } throws expectedException

            val actual = subject.getCharacters()

            expect {
                that(actual.isFailure).isTrue()
                that(actual.exceptionOrNull()).isEqualTo(expectedException)
            }
        }

    @Test
    fun `Given charactersApi returns response with empty body When calling getCharacters Then check we get failure`() =
        runTest {
            coEvery { charactersApi.getCharacters() } returns mockk {
                every { body() } returns null
            }

            val actual = subject.getCharacters()

            assert(actual.isFailure)
        }

    @Test
    fun `Given charactersApi returns response with unsuccessful code When calling getCharacters Then check we get failure`() =
        runTest {
            coEvery { charactersApi.getCharacters() } returns Response.error(
                403,
                mockk(relaxed = true)
            )

            val actual = subject.getCharacters()

            assert(actual.isFailure)
        }

    @Test
    fun `Given charactersApi returns response successful When calling getCharacters Then check we get success`() =
        runTest {
            coEvery { charactersApi.getCharacters() } returns Response.success(emptyList())

            val actual = subject.getCharacters()

            expect {
                that(actual.isSuccess).isTrue()
                that(actual.getOrNull()).isEqualTo(listOf())
            }

        }
}
