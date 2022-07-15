package studio.lunabee.shared

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import lunabee.studio.sqldelight.NoteDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single {
        val driver: SqlDriver = NativeSqliteDriver(NoteDatabase.Schema, "note.db")
        NoteDatabase(driver)
    }
}
