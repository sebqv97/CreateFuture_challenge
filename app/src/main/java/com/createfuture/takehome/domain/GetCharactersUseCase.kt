package com.createfuture.takehome.domain

import com.createfuture.takehome.data.characters.dto.ApiCharacter
import com.createfuture.takehome.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): Result<List<ApiCharacter>> {
        return charactersRepository.getCharacters()
    }
}
