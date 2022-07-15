package studio.lunabee.shared

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import lunabee.studio.sqldelight.UserDatabase

internal actual fun testDbConnection(): SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { UserDatabase.Schema.create(it) }
