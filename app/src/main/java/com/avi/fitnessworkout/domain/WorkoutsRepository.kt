package com.avi.fitnessworkout.domain

import com.avi.fitnessworkout.base.FWResult
import com.avi.fitnessworkout.data.WorkoutsDataSource
import com.avi.fitnessworkout.domain.models.WorkoutPlanEntity
import javax.inject.Inject

class WorkoutsRepository @Inject constructor(
    private val workoutsDataSource: WorkoutsDataSource
) {

    private var workoutPlanCache: WorkoutPlanEntity? = null

    suspend fun getWorkoutPlan(forceRefresh: Boolean): FWResult<WorkoutPlanEntity> {
        return if (forceRefresh || workoutPlanCache == null) {
            val result = workoutsDataSource.getWorkoutPlan()
            if (result is FWResult.Success) {
                workoutPlanCache = result.data
            }
            result
        } else {
            FWResult.Success(workoutPlanCache!!)
        }
    }

}