package studio.lunabee.kmptest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import studio.lunabee.kmptest.di.appModule
import studio.lunabee.kmptest.di.userModule
import studio.lunabee.shared.di.initKoin

class KmpTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@KmpTestApp)
            modules(appModule, userModule)
        }
    }
}