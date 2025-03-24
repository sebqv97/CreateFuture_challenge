package com.createfuture.takehome.domain

import com.createfuture.takehome.data.dto.ApiCharacter
import com.createfuture.takehome.domain.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    operator fun invoke(): Result<ApiCharacter> {
        return charactersRepository.getCharacters()
    }
}
