package studio.lunabee.shared.common

import studio.lunabee.shared.remote.ErrorException

sealed class Result<T>(val data: T? = null, val ex: ErrorException? = null) {

    class Success<T>(data: T) : Result<T>(data = data)
    class Loading<T> : Result<T>()
    class Error<T>(ex: ErrorException) : Result<T>(ex = ex)
}
