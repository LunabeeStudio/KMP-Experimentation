package studio.lunabee.shared

import com.squareup.sqldelight.android.AndroidSqliteDriver
import lunabee.studio.sqldelight.NoteDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single {
        val driver = AndroidSqliteDriver(NoteDatabase.Schema, get(), "note.db")

        NoteDatabase(driver)
    }
}

