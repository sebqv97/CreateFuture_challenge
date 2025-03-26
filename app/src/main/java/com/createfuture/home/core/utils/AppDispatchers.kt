package com.createfuture.home.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Singleton
class AppDispatchers(
    val IO: CoroutineDispatcher = Dispatchers.IO,
    val DEFAULT: CoroutineDispatcher = Dispatchers.Default,
    val MAIN: CoroutineDispatcher = Dispatchers.Main.immediate,
    val UNCONFINED: CoroutineDispatcher = Dispatchers.Unconfined
)
