package studio.lunabee.kmptest.domain.repository

import studio.lunabee.kmptest.domain.model.User
import studio.lunabee.kmptest.domain.result.Result

interface UserRepository {

    suspend fun getUsers(): Result<List<User>>

}