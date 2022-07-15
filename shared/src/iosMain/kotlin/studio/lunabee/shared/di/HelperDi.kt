package studio.lunabee.shared.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import studio.lunabee.shared.domain.repository.UserRepository
import studio.lunabee.shared.domain.use_case.GetNewUser

object HelperDi : KoinComponent {

    val userRepository: UserRepository by inject()
    private val getNewUserUseCase: GetNewUser by inject()

    suspend fun getNewUser(page: Int) = getNewUserUseCase(page = page)
}