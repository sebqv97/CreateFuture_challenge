package com.createfuture.takehome.data.characters.dto

import androidx.annotation.Keep

@Keep
data class ApiCharacter(
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val aliases: List<String>, // mistake here, was 'int'
    val tvSeries: List<String>,
    val playedBy: List<String>,
)
