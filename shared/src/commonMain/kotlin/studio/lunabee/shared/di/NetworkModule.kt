package studio.lunabee.shared.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.shared.remote.UserRemoteDataSourceImpl
import studio.lunabee.shared.remote.ktor.clientEngine
import studio.lunabee.shared.repository.UserRemoteDataSource

fun networkModule(enableNetworkLogs: Boolean): Module = module {

    single { createJson() }

    single { createHttpClient(get(), enableNetworkLogs = enableNetworkLogs, get()) }

    single { clientEngine }

    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }

}

internal fun createHttpClient(
    json: Json,
    enableNetworkLogs: Boolean,
    clientEngine: HttpClientEngine,
): HttpClient = HttpClient(clientEngine) {
    expectSuccess = true
    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}

internal fun createJson(): Json = Json { isLenient = true; ignoreUnknownKeys = true }
