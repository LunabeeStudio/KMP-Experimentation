package studio.lunabee.shared.repository

import studio.lunabee.shared.remote.dto.UserDto

interface UserRemoteDataSource {

    @Throws(Exception::class)
    suspend fun getUsersList(): List<UserDto>

}