package studio.lunabee.kmptest.repository

import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.common.ResourcesProvider
import studio.lunabee.kmptest.domain.model.User
import studio.lunabee.kmptest.domain.model.toModel
import studio.lunabee.kmptest.domain.repository.UserRepository
import studio.lunabee.kmptest.domain.result.Result
import studio.lunabee.shared.remote.dto.UserDto
import studio.lunabee.shared.remote.utils.ErrorException
import studio.lunabee.shared.repository.UserRemoteDataSource

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val resourcesProvider: ResourcesProvider,
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val res: List<UserDto> = remoteDataSource.getUsersList()

            Result.Success(res.map { it.toModel() })
        } catch (e: ErrorException.Server) {
            Result.Error(resourcesProvider.getString(R.string.error_server))
        } catch (e: ErrorException.Network) {
            Result.Error(resourcesProvider.getString(R.string.error_network))
        } catch (e: Exception) {
            Result.Error(resourcesProvider.getString(R.string.error_unexpected))
        }
    }

}