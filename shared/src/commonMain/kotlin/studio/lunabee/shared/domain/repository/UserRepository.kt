package studio.lunabee.shared.domain.repository

import kotlinx.coroutines.flow.Flow
import studio.lunabee.shared.common.Result
import studio.lunabee.shared.domain.model.User

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun fetchNewUser(page: Int): Result<User>

    suspend fun getLastUser(): User?

    suspend fun updateUser(user: User)

    fun getLikedUsers(): Flow<List<User>>

    fun getDislikedUsers(): Flow<List<User>>

}