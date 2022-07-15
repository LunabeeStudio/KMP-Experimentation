package studio.lunabee.shared.remote

sealed class ErrorException(val e: Exception) : Exception() {
    class Server(e: Exception) : ErrorException(e)
    class Network(e: Exception) : ErrorException(e)
    class Unexpected(e: Exception) : ErrorException(e)
}