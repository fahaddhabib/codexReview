package com.example.codexreview.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codexreview.R
import com.example.codexreview.navigation.HomeRoutes
import com.example.codexreview.ui.screens.settings.SettingsScreen
import com.example.codexreview.ui.screens.settings.language.LanguageScreen
import com.example.codexreview.viewmodel.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val homeTabs = listOf(
        HomeTab(route = HomeRoutes.CHAT, label = stringResource(R.string.chat), icon = Icons.Filled.Chat),
        HomeTab(route = HomeRoutes.HUB, label = stringResource(R.string.hub), icon = Icons.Filled.Group),
        HomeTab(route = HomeRoutes.SETTINGS, label = stringResource(R.string.settings), icon = Icons.Filled.Settings)
    )

    LaunchedEffect(currentDestination?.route) {
        currentDestination?.route?.let(viewModel::onTabSelected)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                homeTabs.forEach { tab ->
                    val selected = currentDestination
                        ?.hierarchy
                        ?.any { it.route == tab.route } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            viewModel.onTabSelected(tab.route)
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label
                            )
                        },
                        label = { Text(text = tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoutes.CHAT,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HomeRoutes.CHAT) { HomeTabScreen(label = stringResource(R.string.chat)) }
            composable(HomeRoutes.HUB) { HomeTabScreen(label = stringResource(R.string.hub)) }
            composable(HomeRoutes.SETTINGS) {
                SettingsScreen(onLanguageClick = { navController.navigate(HomeRoutes.LANGUAGE) })
            }
            composable(HomeRoutes.LANGUAGE) {
                LanguageScreen(onBackClick = { navController.popBackStack() })
            }
        }
    }
}

@Composable
private fun HomeTabScreen(label: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label)
    }
}

private data class HomeTab(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
