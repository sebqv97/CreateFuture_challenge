package com.createfuture.home.data.mapper

import com.createfuture.core.utils.substringUntilString
import com.createfuture.home.data.characters.dto.ApiCharacter
import com.createfuture.home.domain.model.GotCharacter
import javax.inject.Inject

class CharacterMapper @Inject constructor(){
    infix fun map(dtoCharacter: ApiCharacter): GotCharacter = with(dtoCharacter) {
        GotCharacter(
            name = name,
            gender = gender.replaceWithNotSpecified(),
            culture = culture.replaceWithNotSpecified(),
            born = born.replaceWithNotSpecified(),
            died = died.shortenDiedString(),
            aliases = aliases,
            tvSeries = tvSeries,
            playedBy = playedBy
        )
    }

    private fun String.replaceWithNotSpecified(): String = ifBlank { NOT_SPECIFIED }


    private fun String.shortenDiedString(): String {
        return if (isBlank()) {
            "Still alive"
        } else {
            substringUntilString("AC", inclusive = true)
        }
    }

    private companion object {
        const val NOT_SPECIFIED = "Not specified"
    }
}
