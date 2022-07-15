package studio.lunabee.shared

import com.squareup.sqldelight.db.SqlDriver

internal expect fun testDbConnection(): SqlDriver
