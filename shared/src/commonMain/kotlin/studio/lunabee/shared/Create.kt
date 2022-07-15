package studio.lunabee.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import studio.lunabee.shared.repository.UserRemoteDataSource

class Init : KoinComponent {

    private val userApi: UserRemoteDataSource by inject()

    fun create(): UserRemoteDataSource = userApi

}