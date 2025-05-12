package com.avi.fitnessworkout.domain.models

import com.google.gson.annotations.SerializedName

data class WorkoutPlanEntity(
    @SerializedName("workouts")
    val workouts: List<WorkoutDayEntity>
)

data class WorkoutDayEntity(
    @SerializedName("day")
    val day: Int,
    @SerializedName("workout")
    val workout: List<ExerciseEntity>
)

data class ExerciseEntity(
    @SerializedName("exercise_id")
    val exerciseId: Int,
    @SerializedName("exercise_name")
    val exerciseName: String,
    @SerializedName("exercise_thumbnail")
    val exerciseThumbnail: String,
    @SerializedName("muscle_group")
    val muscleGroup: String,
    @SerializedName("muscle_group_image")
    val muscleGroupImage: String,
    @SerializedName("amount_of_sets")
    val amountOfSets: Int,
    @SerializedName("rep_range")
    val repRange: String,
    @SerializedName("weight_amount")
    val weightAmount: String?
)