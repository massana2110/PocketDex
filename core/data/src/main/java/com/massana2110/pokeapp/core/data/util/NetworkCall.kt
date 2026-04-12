package com.massana2110.pokeapp.core.data.util

import com.massana2110.pokeapp.core.domain.exception.ApiException
import com.massana2110.pokeapp.core.network.util.NetworkResult
import com.massana2110.pokeapp.core.network.util.safeRequest
import retrofit2.Response

suspend fun <T> networkCall(request: suspend () -> Response<T>): Result<T> =
    when (val result = safeRequest(request)) {
        is NetworkResult.Success -> Result.success(result.data)
        is NetworkResult.Error -> Result.failure(ApiException(result.code, result.message))
        is NetworkResult.NetworkException -> Result.failure(result.throwable)
    }
