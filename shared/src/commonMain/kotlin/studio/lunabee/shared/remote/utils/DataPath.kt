package studio.lunabee.shared.remote.utils

private const val BASE_URL = "https://jobapplicationtest.lunabee.studio"

internal sealed class DataPath(val route: String) {
    object Users : DataPath("$BASE_URL/users")
}
