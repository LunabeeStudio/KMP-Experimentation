package studio.lunabee.kmptest.di

import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.kmptest.domain.repository.UserRepository
import studio.lunabee.kmptest.domain.use_case.FetchUsers
import studio.lunabee.kmptest.repository.UserRepositoryImpl

val userModule: Module = module {

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    single { FetchUsers(get()) }

}