package com.avi.fitnessworkout.ui.pages.myworkout

import com.avi.fitnessworkout.domain.models.ExerciseEntity
import com.avi.fitnessworkout.domain.models.WorkoutDayEntity
import com.avi.fitnessworkout.domain.models.WorkoutPlanEntity

val MockWorkoutData  = WorkoutPlanEntity(
    listOf(
        WorkoutDayEntity(
            day = 1,
            isCompleted = false,
            workouts = listOf(
                ExerciseEntity(
                    exerciseId = 1,
                    exerciseName = "Push Up And Pull Up and Squat and Bench Press and Lorem Ipsum and Lorem Ipsum and Lorem Ipsum",
                    exerciseThumbnail = "",
                    muscleGroup = "Chest",
                    muscleGroupImage = "",
                    amountOfSets = 3,
                    repRange = "10-15",
                    weightAmount = null
                ),
                ExerciseEntity(
                    exerciseId = 2,
                    exerciseName = "Squat",
                    exerciseThumbnail = "",
                    muscleGroup = "Legs",
                    muscleGroupImage = "",
                    amountOfSets = 3,
                    repRange = "10-15",
                    weightAmount = null
                )
            )
        ),
        WorkoutDayEntity(
            day = 2,
            isCompleted = true,
            workouts = listOf(
                ExerciseEntity(
                    exerciseId = 3,
                    exerciseName = "Deadlift",
                    exerciseThumbnail = "",
                    muscleGroup = "Back",
                    muscleGroupImage = "",
                    amountOfSets = 3,
                    repRange = "10-15",
                    weightAmount = null
                )
            )
        ),
        WorkoutDayEntity(
            day = 3,
            isCompleted = false,
            workouts = listOf(
                ExerciseEntity(
                    exerciseId = 4,
                    exerciseName = "Bench Press",
                    exerciseThumbnail = "",
                    muscleGroup = "Chest",
                    muscleGroupImage = "",
                    amountOfSets = 3,
                    repRange = "10-15",
                    weightAmount = null
                ),
                ExerciseEntity(
                    exerciseId = 5,
                    exerciseName = "Lunges",
                    exerciseThumbnail = "",
                    muscleGroup = "Legs",
                    muscleGroupImage = "",
                    amountOfSets = 3,
                    repRange = "10-15",
                    weightAmount = null
                )
            )
        )
    )
)