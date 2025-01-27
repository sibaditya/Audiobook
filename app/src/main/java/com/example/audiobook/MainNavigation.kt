package com.example.audiobook

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.audiobook.model.Podcasts
import com.example.audiobook.viewmodel.PodcastListViewModel
import com.example.audiobook.screens.AboutScreen
import com.example.audiobook.screens.ArticlesScreen
import com.example.audiobook.screens.PodcastsDetailScreen
import com.example.audiobook.screens.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Enum for defining route values
enum class MainRoute(value: String) {
    Articles("articles"),
    About("about"),
    Settings("settings"),
    PodcastDetail("podcastDetail")
}

// Data class to represent a menu item
private data class DrawerMenu(val icon: ImageVector, val title: String, val route: String)

@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach {
            NavigationDrawerItem(
                label = { Text(text = it.title) },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }
    }
}

@Composable
fun MainNavigation(
    viewModel: PodcastListViewModel,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    // Define menu items
    val menus = arrayOf(
        DrawerMenu(Icons.Filled.Face,  LocalContext.current.getString(R.string.list_of_podcasts), MainRoute.Articles.name),
        DrawerMenu(Icons.Filled.Settings,  LocalContext.current.getString(R.string.settings), MainRoute.Settings.name),
        DrawerMenu(Icons.Filled.Info,  LocalContext.current.getString(R.string.about), MainRoute.About.name)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }

                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = MainRoute.Articles.name) {
            composable(MainRoute.Articles.name) {
                ArticlesScreen(drawerState, viewModel, navController)
            }
            composable(MainRoute.About.name) {
                AboutScreen(drawerState)
            }
            composable(MainRoute.Settings.name) {
                SettingsScreen(drawerState)
            }
            composable(route = MainRoute.PodcastDetail.name) {
                val podcast = navController.previousBackStackEntry?.savedStateHandle?.get<Podcasts>("podcast")
                PodcastsDetailScreen(podcast, navController)
            }
        }
    }
}
