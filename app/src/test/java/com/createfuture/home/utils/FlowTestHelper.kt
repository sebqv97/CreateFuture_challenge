package com.createfuture.home.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest

suspend fun <T> Flow<T>.test(block: suspend (List<T>) -> Unit) = runTest {
    val results = mutableListOf<T>()
    val job = launch { this@test.toList(results) }
    block(results)
    job.cancel()
}