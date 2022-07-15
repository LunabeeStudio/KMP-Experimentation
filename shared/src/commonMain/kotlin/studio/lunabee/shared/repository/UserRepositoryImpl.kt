package studio.lunabee.shared.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerializationException
import studio.lunabee.shared.common.Result
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.domain.model.toEntity
import studio.lunabee.shared.domain.model.toModel
import studio.lunabee.shared.domain.repository.UserRepository
import studio.lunabee.shared.local.UserDatabaseHelper
import studio.lunabee.shared.remote.DataPath
import studio.lunabee.shared.remote.ErrorException
import studio.lunabee.shared.remote.dto.UserDto

class UserRepositoryImpl(
    private val userDatabaseHelper: UserDatabaseHelper,
    private val client: HttpClient,
) : UserRepository {

    override suspend fun saveUser(user: User): Unit = userDatabaseHelper.insertUser(user.toEntity())

    override suspend fun fetchNewUser(page: Int): Result<User> = try {
        val response: HttpResponse = client.get(DataPath.Users.route) {
            parameter(DataPath.Users.page, page)
            parameter(DataPath.Users.pageSize, 1)
        }

        Result.Success(response.body<List<UserDto>>()[0].toModel())

    } catch (ex: HttpRequestTimeoutException) {
        Result.Error(ex = ErrorException.Network(ex))

    } catch (ex: ClientRequestException) {
        Result.Error(ex = ErrorException.Network(ex))

    } catch (ex: IOException) {
        Result.Error(ex = ErrorException.Network(ex))

    } catch (ex: SerializationException) {
        Result.Error(ex = ErrorException.Server(ex))

    } catch (ex: ServerResponseException) {
        Result.Error(ex = ErrorException.Server(ex))

    } catch (ex: Exception) {
        Result.Error(ex = ErrorException.Unexpected(ex))
    }

    override suspend fun getLastUser(): User? = userDatabaseHelper.getLastUser()?.toModel()

    override suspend fun updateUser(user: User): Unit = userDatabaseHelper.updateUser(user.toEntity())

    override fun getLikedUsers(): Flow<List<User>> = userDatabaseHelper.getLikedUsers()

    override fun getDislikedUsers(): Flow<List<User>> = userDatabaseHelper.getDislikedUsers()
}
