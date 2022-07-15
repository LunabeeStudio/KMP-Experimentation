package studio.lunabee.shared.di.source

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import lunabee.studio.sqldelight.UserDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun databaseModule(): Module = module {
    single {
        val driver: SqlDriver = NativeSqliteDriver(UserDatabase.Schema, "user.db")
        UserDatabase(driver)
    }
}