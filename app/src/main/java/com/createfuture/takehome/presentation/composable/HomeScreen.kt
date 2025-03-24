package com.createfuture.takehome.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.createfuture.takehome.R
import com.createfuture.takehome.presentation.HomeUiState
import com.createfuture.takehome.presentation.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    LaunchedEffect(true) {
        viewModel.loadCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.img_characters),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        val state by viewModel.uiState.collectAsState(initial = HomeUiState(isLoading = true))

        if (state.isLoading) {
            LoadingSpinner()
        } else if (state.error != null) {
            state.error?.let {
                ErrorScreen(it)
            }
        } else {
            if (state.characters.isNotEmpty()) {
                for (character in state.characters) {
                    Row {
                        Spacer(modifier = Modifier.size(16.dp))
                        Column {
                            Text(text = character.name, color = Color.White, fontSize = 16.sp)
                            Row {
                                Text(text = "Culture: ", color = Color.White, fontSize = 16.sp)
                                Text(
                                    text = character.culture,
                                    color = Color.Transparent,
                                    fontSize = 16.sp
                                )
                            }
                            Row {
                                Text("Born: ", color = Color.White, fontSize = 16.sp)
                                Text(text = character.born, color = Color.White, fontSize = 16.sp)
                            }
                            Row {
                                Text(text = "Died: ", color = Color.White, fontSize = 16.sp)
                                Text(text = character.died, color = Color.White, fontSize = 16.sp)
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column {
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
                            }
                            Text(seasons, color = Color.White, fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}
