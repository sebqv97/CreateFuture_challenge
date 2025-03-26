package com.createfuture.home.domain

import com.createfuture.home.data.characters.dto.ApiCharacter
import com.createfuture.home.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): Result<List<ApiCharacter>> {
        return charactersRepository.getCharacters()
    }
}
