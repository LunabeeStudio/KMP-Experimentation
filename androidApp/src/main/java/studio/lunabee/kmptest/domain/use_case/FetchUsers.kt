package studio.lunabee.kmptest.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import studio.lunabee.kmptest.domain.model.User
import studio.lunabee.kmptest.domain.repository.UserRepository
import studio.lunabee.kmptest.domain.result.Result

class FetchUsers(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Result<List<User>>> = flow {
        emit(Result.Loading())
        val res = userRepository.getUsers()
        emit(res)
    }
}