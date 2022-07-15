package studio.lunabee.shared.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import studio.lunabee.shared.di.source.clientModule
import studio.lunabee.shared.di.source.databaseModule
import studio.lunabee.shared.di.source.networkModule

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}): KoinApplication =
    startKoin {
        appDeclaration()
        modules(
            databaseModule(),
            clientModule(),
            networkModule(enableNetworkLogs),
            userModule(),
        )
    }