package com.createfuture.home.domain.model

data class GotCharacter(
val name: String,
val gender: String,
val culture: String,
val born: String,
val died: String,
val aliases: List<String>,
val tvSeries: List<String>,
val playedBy: List<String>
)
