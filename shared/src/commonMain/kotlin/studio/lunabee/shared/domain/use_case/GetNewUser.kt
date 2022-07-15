package studio.lunabee.shared.domain.use_case

import studio.lunabee.shared.common.Result
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.domain.repository.UserRepository

interface GetNewUser {
    suspend operator fun invoke(page: Int): Result<User>
}

class GetNewUserImpl(private val userRepository: UserRepository) : GetNewUser {

    override suspend operator fun invoke(page: Int): Result<User> {
        val retrieveUser: User? = userRepository.getLastUser()

        if (retrieveUser != null) return Result.Success(retrieveUser)

        val data: Result<User> = userRepository.fetchNewUser(page)

        if (data is Result.Success)
            userRepository.saveUser(data.data!!)
        return data
    }
}