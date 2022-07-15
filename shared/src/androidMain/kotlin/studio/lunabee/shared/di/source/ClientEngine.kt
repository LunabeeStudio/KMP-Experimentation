package studio.lunabee.shared.di.source

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

internal actual fun clientModule(): Module = module {
    single {
        OkHttp.create {
            config {
                connectTimeout(5, TimeUnit.SECONDS)
            }
        }
    }
}
