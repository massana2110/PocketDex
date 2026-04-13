package com.massana2110.pokeapp.core.network.util

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int, val message: String) : NetworkResult<Nothing>()
    data class NetworkException(val throwable: Throwable) : NetworkResult<Nothing>()
}
