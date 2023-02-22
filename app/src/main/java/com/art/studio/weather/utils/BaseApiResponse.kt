package com.art.studio.weather.utils

import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> saveApiCall(api: suspend () -> Response<T>):ResultStatus<T>{
        try {
            val response = api()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    return ResultStatus.Success(body)
                }?: return errorMessage("Body isEmpty")
            } else {
                return errorMessage("${response.code()} and ${response.message()}")
            }
        } catch (e: Exception){
            return errorMessage(e.message.toString())
        }
    }

    private fun <T> errorMessage(message: String): ResultStatus.Error<T> =
        ResultStatus.Error(data = null, message = "Error failed: $message")
}