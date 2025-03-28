package com.createfuture.home.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.createfuture.core.utils.removeLastOccurrenceOf
import com.createfuture.home.domain.model.GotCharacter

@Composable
fun RenderCharacters(characters: List<GotCharacter>) {
    val charValueModifier = Modifier.alpha(0.5f)
    if (characters.isNotEmpty()) {
        Spacer(modifier = Modifier.size(16.dp))

        for (character in characters) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = character.name, color = Color.White, fontSize = 20.sp)
                    Spacer(Modifier.size(8.dp))
                    Row {
                        Text(text = "Culture: ", color = Color.White, fontSize = 16.sp)
                        Text(
                            text = character.culture,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = charValueModifier
                        )
                    }
                    Row {
                        Text("Born: ", color = Color.White, fontSize = 16.sp)
                        Text(
                            text = character.born,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = charValueModifier
                        )
                    }
                    Row {
                        Text(text = "Died: ", color = Color.White, fontSize = 16.sp)
                        Text(
                            text = character.died,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = charValueModifier
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.End) {
                    Text("Seasons: ", color = Color.White, fontSize = 14.sp)
                    var seasons = character.tvSeries.joinToString {
                        when (it) {
                            "Season 1" -> "I "
                            "Season 2" -> "II, "
                            "Season 3" -> "III, "
                            "Season 4" -> "IV, "
                            "Season 5" -> "V, "
                            "Season 6" -> "VI, "
                            "Season 7" -> "VII, "
                            "Season 8" -> "VIII"
                            else -> ""
                        }
                    }.removeLastOccurrenceOf(",")
                    Text(
                        seasons,
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = charValueModifier
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                color = Color.White
            )
        }
    }
}
