package studio.lunabee.shared.remote.utils

sealed class ErrorException(val e: Exception) : Exception() {
    class Server(e: Exception) : ErrorException(e)
    class Network(e: Exception) : ErrorException(e)
}
