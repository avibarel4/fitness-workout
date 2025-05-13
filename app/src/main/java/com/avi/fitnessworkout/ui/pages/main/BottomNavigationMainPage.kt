package com.avi.fitnessworkout.ui.pages.main

import com.avi.fitnessworkout.R
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.avi.fitnessworkout.ui.pages.myworkout.MyWorkoutPage
import com.avi.fitnessworkout.ui.theme.FWColor

enum class TabPages(
    @StringRes val text: Int,
    @DrawableRes val icon: Int?,
    val route: String
) {
    MyWorkouts(
        text = R.string.main_tabs_my_workout,
        icon = null, //R.drawable.ic_my_workouts,
        route = "tab_my_Workouts"
    ),
    Exercises(
        text = R.string.main_tabs_exercises,
        icon = null,
        route = "tab_exercises"
    ),
    Progress(
        text = R.string.main_tabs_progress,
        icon = null,
        route = "tab_progress"
    ),
    Settings(
        text = R.string.main_tabs_settings,
        icon = null,
        route = "tab_settings"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationMainPage() {
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) }

    var selectedItem by remember { mutableStateOf(TabPages.MyWorkouts) }

    LaunchedEffect(selectedIndex) {
        selectedItem = TabPages.entries[selectedIndex]
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(selectedItem.text),
                        style = MaterialTheme.typography.titleLarge,
                        color = FWColor.White
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                TabPages.entries.forEachIndexed { index, item ->
                    val text = stringResource(item.text)
                    NavigationBarItem(
                        icon = {
                            item.icon?.let {
                                Icon(
                                    painterResource(id = it),
                                    contentDescription = text
                                )
                            } ?: Icon(
                                imageVector = Icons.Default.Build,
                                contentDescription = text
                            )
                        },
                        label = { Text(text) },
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        val graph = navController.createGraph(startDestination = TabPages.MyWorkouts.route) {
            composable(route = TabPages.MyWorkouts.route) {
                MyWorkoutPage()
            }
            composable(route = TabPages.Exercises.route) {
                UnderConstructionScreen()
            }
            composable(route = TabPages.Progress.route) {
                UnderConstructionScreen()
            }
            composable(route = TabPages.Settings.route) {
                UnderConstructionScreen()
            }
        }
        
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)            
        )
    }
}

@Composable
fun UnderConstructionScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Under Construction", color = FWColor.White, style = MaterialTheme.typography.titleLarge)
    }
}