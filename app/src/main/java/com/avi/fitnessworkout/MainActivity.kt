package com.avi.fitnessworkout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.avi.fitnessworkout.ui.pages.main.BottomNavigationMainPage
import com.avi.fitnessworkout.ui.theme.FitnessWorkoutTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessWorkoutTheme {
                BottomNavigationMainPage()
            }
        }
    }
}

@Preview
@Composable
private fun BottomNavigationMainPagePreview() {
    FitnessWorkoutTheme {
        BottomNavigationMainPage()
    }
}