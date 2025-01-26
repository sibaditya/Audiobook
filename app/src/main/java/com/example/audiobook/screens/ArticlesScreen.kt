package com.mvvmJetpackCompose.navigation.screens

import PodcastScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.audiobook.CustomAppBar
import com.example.audiobook.R
import com.example.audiobook.viewmodel.PodcastListViewModel

@Composable
fun ArticlesScreen(drawerState: DrawerState, viewModel: PodcastListViewModel) {
    // Scaffold is a Material Design layout structure that
    // provides the app's basic structure, like top bar and drawers.
    Scaffold(
        topBar = {
            // CustomAppBar is a custom composable function that defines the top bar.
            CustomAppBar(
                drawerState = drawerState, // Pass the drawer state to enable opening the drawer.
                title = LocalContext.current.getString(R.string.list_of_podcasts) // Set the title of the top bar.
            )
        }
    ) { paddingValues ->
        // Column is a composable that arranges its children vertically.
        Column(
            modifier = Modifier
                .fillMaxSize() // Fill the entire available space.
                .padding(paddingValues), // Apply padding values.
            horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally.
            verticalArrangement = Arrangement.Center // Center vertically.
        ) {
            // PodcastScreen is another composable function that is
            // included in the Column to display podcast information.
            PodcastScreen(viewModel = viewModel)
        }
    }
}
