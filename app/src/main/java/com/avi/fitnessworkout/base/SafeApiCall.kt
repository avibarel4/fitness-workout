package com.avi.fitnessworkout.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): FWResult<T> = withContext(dispatcher) {
    try {
        FWResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        FWResult.Error(throwable.message)
    }
}