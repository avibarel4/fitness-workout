package com.avi.fitnessworkout.base

sealed class FWState<out T : Any> {
    open val data: T? = null

    data object Empty : FWState<Nothing>()
    data class Loading<out T : Any>(override val data: T? = null) : FWState<T>()
    data class Data<out T : Any>(override val data: T) : FWState<T>()
    data class Error<out T : Any>(val error: Throwable?, override val data: T? = null) : FWState<T>()
}
