package com.createfuture.takehome.core.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDispatchers @Inject constructor() {
    val IO = Dispatchers.IO
    val DEFAULT = Dispatchers.Default
    val MAIN = Dispatchers.Main.immediate
    val UNCONFINED = Dispatchers.Unconfined
}