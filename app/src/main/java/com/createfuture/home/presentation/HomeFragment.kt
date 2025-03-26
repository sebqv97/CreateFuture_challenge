package com.createfuture.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView =
        ComposeView(requireContext()).apply {
            setContent {
                Scaffold { contentPadding ->
                    HomeScreen(
                        viewModel<HomeViewModel>(),
                        modifier = Modifier.padding(contentPadding)
                    )
                }
            }
        }
}
