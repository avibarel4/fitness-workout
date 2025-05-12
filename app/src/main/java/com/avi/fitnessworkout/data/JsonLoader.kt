package com.avi.fitnessworkout.data

import android.content.Context
import com.avi.fitnessworkout.domain.models.WorkoutPlanEntity
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class JsonLoader @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun loadWorkoutPlan(): WorkoutPlanEntity {
        val jsonString = context.assets.open("workouts.json")
            .bufferedReader()
            .use { it.readText() }

        return Gson().fromJson(jsonString, WorkoutPlanEntity::class.java)
    }

}