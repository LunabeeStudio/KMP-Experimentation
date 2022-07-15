package studio.lunabee.shared.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}): KoinApplication =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs = enableNetworkLogs),
        )
    }

fun initKoin(enableNetworkLogs: Boolean): KoinApplication = initKoin(enableNetworkLogs = enableNetworkLogs) {}