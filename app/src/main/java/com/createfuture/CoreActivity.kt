package com.createfuture

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.createfuture.core.utils.safeCollect
import com.createfuture.home.presentation.HomeScreen
import com.createfuture.home.presentation.HomeViewModel
import com.createfuture.login.presentation.LoginScreen
import com.createfuture.login.presentation.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //needed for Android 15 compatibility

        setContent {
            val navController = rememberNavController()
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val loginViewModel = hiltViewModel<LoginViewModel>()
            subscribeToViewModels(navController, homeViewModel, loginViewModel)
            Scaffold { contentPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Routes.Login
                ) {
                    composable<Routes.Login> {
                        LoginScreen(loginViewModel, Modifier.padding(contentPadding))
                    }
                    composable<Routes.Home> {
                        HomeScreen(homeViewModel, Modifier.padding(contentPadding))
                    }
                }

            }
        }
    }

    private fun subscribeToViewModels(
        navController: NavHostController,
        homeViewModel: HomeViewModel,
        loginViewModel: LoginViewModel
    ) {
        safeCollect(flow = loginViewModel.events, onEach = { handleLoginEvents(navController, it) })
    }

    private fun handleLoginEvents(
        navController: NavHostController,
        event: LoginViewModel.LoginEvents
    ) {
        when (event) {
            is LoginViewModel.LoginEvents.Navigation -> handleLoginNavigation(navController, event)
        }
    }

    private fun handleLoginNavigation(
        navController: NavHostController,
        event: LoginViewModel.LoginEvents.Navigation
    ) {
        when (event) {
            LoginViewModel.LoginEvents.Navigation.NavigateToHome -> navController.navigate(Routes.Home) {
                popUpTo(Routes.Login) { inclusive = true }
            }
        }
    }
}

sealed interface Routes {
    @Serializable
    object Login : Routes

    @Serializable
    object Home : Routes
}
