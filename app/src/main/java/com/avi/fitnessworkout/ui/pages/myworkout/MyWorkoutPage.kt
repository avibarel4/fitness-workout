package com.avi.fitnessworkout.ui.pages.myworkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.avi.fitnessworkout.base.FWState
import com.avi.fitnessworkout.domain.models.ExerciseEntity
import com.avi.fitnessworkout.domain.models.WorkoutDayEntity
import com.avi.fitnessworkout.domain.models.WorkoutPlanEntity
import com.avi.fitnessworkout.ui.theme.FWColor
import com.avi.fitnessworkout.ui.theme.FitnessWorkoutTheme

@Composable
fun MyWorkoutPage(
    viewModel: MyWorkoutViewModel = hiltViewModel()
) {
    val workoutState by viewModel.workoutState.collectAsState()

    when (val state = workoutState) {
        is FWState.Data -> {
            workoutState.data?.let {
                PageData(it)
            } ?: TODO()
        }
        FWState.Empty -> TODO()
        is FWState.Error -> TODO()
        is FWState.Loading -> CircularProgressIndicator()
    }

}

@Composable
private fun PageData(workoutPlan: WorkoutPlanEntity) {
    var selectedDay by remember { mutableStateOf(workoutPlan.workouts.first()) }

    Column(Modifier
        .fillMaxSize()
        .background(color = FWColor.Gray)
    ) {
        DaysRow(workoutPlan.workouts) { selectedDay = it }
        Spacer(Modifier.height(16.dp))
        Details()
        Spacer(Modifier.height(16.dp))
        Exercises(selectedDay.workouts)
    }
}

@Composable
private fun DaysRow(workouts: List<WorkoutDayEntity>?, onDaySelected: (WorkoutDayEntity) -> Unit = {}) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(workouts.orEmpty()) { workout ->
            Spacer(Modifier.width(6.dp))
            Box(
                Modifier
                    .width(80.dp)
                    .height(50.dp)
                    .background(FWColor.LightGray, shape = RoundedCornerShape(25.dp))
                    .clickable { onDaySelected.invoke(workout) },
                contentAlignment = Alignment.Center
            ) {
                if (workout.isCompleted) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "", tint = FWColor.LightBlue)
                } else {
                    Text(text = "Day ${workout.day}", color = FWColor.LightBlue)
                }
            }
            Spacer(Modifier.width(6.dp))
        }
    }
}

@Composable
private fun Details() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Workout Details", color = FWColor.LightBlue)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(FWColor.LightGray, shape = RoundedCornerShape(50.dp)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = FWColor.LightBlue)
        }
    }
}

@Composable
private fun Exercises(exercises: List<ExerciseEntity>) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            Box(Modifier.background(FWColor.GeneralBackground, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))) {
                Spacer(Modifier.height(16.dp))
                Text("Summary", color = FWColor.White, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
        itemsIndexed(exercises) { index, exercise ->
            val bottomShape = if (index == exercises.lastIndex)
                RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            else
                RoundedCornerShape(0.dp)
            Box(Modifier.background(FWColor.GeneralBackground, shape = bottomShape)) {
                ExerciseListItem(exercise)
            }
        }
    }
}

@Composable
fun ExerciseListItem(exercise: ExerciseEntity) {
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(80.dp)) {
            // IMAGE
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = exercise.exerciseName,
                color = FWColor.White,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${exercise.amountOfSets} sets",
                color = FWColor.White.copy(0.8f),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(Modifier.size(50.dp)) {

        }
    }
}

@Preview
@Composable
private fun MyWorkoutPagePreview() {
    FitnessWorkoutTheme {
        PageData(MockWorkoutData)
    }
}