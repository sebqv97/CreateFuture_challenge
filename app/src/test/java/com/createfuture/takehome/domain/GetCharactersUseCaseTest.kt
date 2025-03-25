package com.createfuture.takehome.domain

import com.createfuture.takehome.domain.repository.CharactersRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.math.exp

class GetCharactersUseCaseTest {

    private val charactersRepository = mockk<CharactersRepository>()
    private lateinit var subject: GetCharactersUseCase

    @Before
    fun setUp() {
        subject = GetCharactersUseCase(charactersRepository)
    }

    @Test
    fun `Given repository returns failure When getCharacters is invoked Then check as expected`() =
        runTest {
            val expectedException = IOException()
            coEvery { charactersRepository.getCharacters() } returns Result.failure(expectedException)

            val actual = subject.invoke()

            expectThat(actual).isEqualTo(Result.failure(expectedException))
        }
}
