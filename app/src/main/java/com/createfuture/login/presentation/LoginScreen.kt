package com.createfuture.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.createfuture.takehome.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.img_characters),
                contentScale = ContentScale.FillBounds
            )
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {loginViewModel.onLoginPressed()}) {
            Text("Login")
        }
    }
}