package com.avi.fitnessworkout.base

sealed class FWResult<out T> {
    data class Success<T>(val data: T) : FWResult<T>()
    data class Error(val message: Throwable) : FWResult<Nothing>()
}

fun <T> FWResult<T>.asSuccessValueOrNull(): T? = (this as? FWResult.Success)?.data
fun <T> FWResult<T>.asErrorOrNull() = this as? FWResult.Error
fun <T> isSuccess(result: FWResult<T>) = result is FWResult.Success
fun <T> isError(result: FWResult<T>) = result is FWResult.Error