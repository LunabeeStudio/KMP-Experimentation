package studio.lunabee.shared.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.utils.io.errors.IOException
import studio.lunabee.shared.remote.dto.UserDto
import studio.lunabee.shared.remote.utils.DataPath
import studio.lunabee.shared.remote.utils.ErrorException
import studio.lunabee.shared.repository.UserRemoteDataSource

internal class UserRemoteDataSourceImpl(private val client: HttpClient) : UserRemoteDataSource {

    @Throws(Exception::class)
    override suspend fun getUsersList(): List<UserDto> {
        return try {
            client.get(DataPath.Users.route).body()

        } catch (ex: ResponseException) {
            throw ErrorException.Server(ex)

        } catch (ex: IOException) {
            throw ErrorException.Network(ex)
        }
    }

}
