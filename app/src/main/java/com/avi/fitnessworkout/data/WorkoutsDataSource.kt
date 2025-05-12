package com.avi.fitnessworkout.data

import com.avi.fitnessworkout.base.FWResult
import com.avi.fitnessworkout.base.safeApiCall
import com.avi.fitnessworkout.domain.models.WorkoutPlanEntity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class WorkoutsDataSource @Inject constructor(
    private val jsonLoader: JsonLoader
) {

    suspend fun getWorkoutPlan(): FWResult<WorkoutPlanEntity> {
        return safeApiCall(Dispatchers.IO) {
            jsonLoader.loadWorkoutPlan()
        }
    }

}