package com.createfuture.home.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ShowError(throwable: Throwable) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val message = throwable.message ?: genericMessage


        Text(text = message, textAlign = TextAlign.Center, color = Color.Red)
    }
}

private const val genericMessage = "There has been a problem. Please try again later"