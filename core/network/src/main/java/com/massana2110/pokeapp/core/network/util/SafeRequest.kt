package com.massana2110.pokeapp.core.network.util

import retrofit2.Response

suspend fun <T> safeRequest(request: suspend () -> Response<T>): NetworkResult<T> =
    runCatching { request() }
        .fold(
            onSuccess = { response ->
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error(
                        code = response.code(),
                        message = response.message()
                    )
                }
            },
            onFailure = { throwable ->
                NetworkResult.NetworkException(throwable)
            }
        )
