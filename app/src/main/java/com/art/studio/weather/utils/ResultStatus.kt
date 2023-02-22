package com.art.studio.weather.utils

sealed class ResultStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?): ResultStatus<T>(data)
    class Error<T>(data: T?, message: String?): ResultStatus<T>(data, message)
    class Loading<T>(): ResultStatus<T>()
}