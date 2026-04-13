package com.massana2110.pokeapp.core.domain.exception

class ApiException(val code: Int, message: String) : Exception(message)
