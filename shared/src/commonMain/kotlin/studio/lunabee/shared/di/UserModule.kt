package studio.lunabee.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.shared.domain.repository.UserRepository
import studio.lunabee.shared.domain.use_case.GetNewUser
import studio.lunabee.shared.domain.use_case.GetNewUserImpl
import studio.lunabee.shared.local.UserDatabaseHelper
import studio.lunabee.shared.repository.UserRepositoryImpl

internal fun userModule(): Module = module {

    single { UserDatabaseHelper(get()) }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    single<GetNewUser> { GetNewUserImpl(get()) }

}

