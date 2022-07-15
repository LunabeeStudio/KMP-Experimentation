package studio.lunabee.kmptest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import studio.lunabee.kmptest.di.appModule
import studio.lunabee.shared.di.initKoin

class KMPTestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@KMPTestApp)
            modules(appModule)
        }
    }
}