package com.createfuture.core.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> AppCompatActivity.safeCollect(
    activeState: Lifecycle.State = Lifecycle.State.STARTED,
    flow: Flow<T>,
    onEach: (T) -> Unit
) {
    flow.flowWithLifecycle(lifecycle, activeState).onEach(onEach).launchIn(lifecycleScope)
}