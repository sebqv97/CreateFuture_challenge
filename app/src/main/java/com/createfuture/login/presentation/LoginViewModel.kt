package com.createfuture.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.createfuture.core.utils.AppDispatchers
import com.createfuture.login.domain.usecase.HasUserLoggedInUseCase
import com.createfuture.login.domain.usecase.RetrieveAndSafeCharactersApiKeyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val retrieveAndSafeCharactersApiKeyUseCase: RetrieveAndSafeCharactersApiKeyUseCase,
    private val hasUserLoggedInUseCase: HasUserLoggedInUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {
    private val _events = MutableSharedFlow<LoginEvents>()
    val events: Flow<LoginEvents> get() = _events

    init {
        viewModelScope.launch {
            val hasUserLoggedIn = withContext(appDispatchers.IO) {
                hasUserLoggedInUseCase.invoke()
            }
            if (hasUserLoggedIn) {
                _events.emit(LoginEvents.Navigation.NavigateToHome)
            }
        }
    }

    fun onLoginPressed() {
        viewModelScope.launch {
                withContext(appDispatchers.IO) {
                    retrieveAndSafeCharactersApiKeyUseCase()
                }

                _events.emit(LoginEvents.Navigation.NavigateToHome)
        }
    }

    sealed interface LoginEvents {
        sealed interface Navigation : LoginEvents {
            object NavigateToHome : Navigation
        }
    }
}