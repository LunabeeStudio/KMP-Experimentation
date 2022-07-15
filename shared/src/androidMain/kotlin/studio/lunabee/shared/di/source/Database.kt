package studio.lunabee.shared.di.source

import com.squareup.sqldelight.android.AndroidSqliteDriver
import lunabee.studio.sqldelight.UserDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun databaseModule(): Module = module {
    single {
        val driver = AndroidSqliteDriver(UserDatabase.Schema, get(), "user.db")

        UserDatabase(driver)
    }
}