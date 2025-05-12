package com.avi.fitnessworkout.ui.pages.myworkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avi.fitnessworkout.base.FWResult
import com.avi.fitnessworkout.base.FWState
import com.avi.fitnessworkout.domain.WorkoutsRepository
import com.avi.fitnessworkout.domain.models.WorkoutPlanEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyWorkoutViewModel @Inject constructor(
    private val workoutsRepository: WorkoutsRepository
) : ViewModel() {

    private val _workoutState = MutableStateFlow<FWState<WorkoutPlanEntity>>(FWState.Empty)
    val workoutState: StateFlow<FWState<WorkoutPlanEntity>> = _workoutState

    init {
        fetchWorkoutPlan(false)
    }

    private fun fetchWorkoutPlan(forceRefresh: Boolean) {
        viewModelScope.launch {
            _workoutState.value = FWState.Loading()
            when (val result = workoutsRepository.getWorkoutPlan(forceRefresh)) {
                is FWResult.Error -> _workoutState.value = FWState.Error(result.message)
                is FWResult.Success -> _workoutState.value = FWState.Data(result.data)
            }
        }
    }

}