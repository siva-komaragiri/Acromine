package app.sivakomaragiri.acromine.netwok

import java.lang.Exception

sealed class NetworkResult<out T: Any> {
    data class Success<out T:Any>(val data: T?): NetworkResult<T>()
    data class Error<out T:Any>(val message: String, val data: T? = null): NetworkResult<T>()
}
