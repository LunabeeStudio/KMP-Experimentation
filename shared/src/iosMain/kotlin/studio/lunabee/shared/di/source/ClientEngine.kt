package studio.lunabee.shared.di.source

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun clientModule(): Module = module {
    single {
        Darwin.create {
            configureSession {
                timeoutIntervalForResource = 5.0
            }
        }
    }
}