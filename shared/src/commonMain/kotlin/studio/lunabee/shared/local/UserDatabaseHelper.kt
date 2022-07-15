package studio.lunabee.shared.local

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import lunabee.studio.sqldelight.UserDatabase
import lunabee.studio.sqldelight.UserEntitiy
import lunabee.studio.sqldelight.UserQueries
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.domain.model.localToModel

class UserDatabaseHelper(
    db: UserDatabase,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    private val queries: UserQueries = db.userQueries

    fun insertUser(user: UserEntitiy) {
        queries.insertUser(user)
    }

    fun updateUser(user: UserEntitiy) {
        queries.updateUser(id = user.id, liked = user.liked)
    }

    fun getLastUser(): UserEntitiy? {
        return queries.getLastUser().executeAsOneOrNull()
    }

    fun getLikedUsers(): Flow<List<User>> = queries
        .getLikedUsers()
        .asFlow()
        .transform { user: Query<UserEntitiy> ->
            emit(user.executeAsList().localToModel())
        }
        .flowOn(backgroundDispatcher)

    fun getDislikedUsers(): Flow<List<User>> = queries
        .getDislikedUsers()
        .asFlow()
        .transform { user ->
            emit(user.executeAsList().localToModel())
        }
        .flowOn(backgroundDispatcher)

    fun deleteAll() {
        queries.deleteAll()
    }

    fun selectAll(): List<UserEntitiy> {
        return queries.selectAll().executeAsList()
    }

    fun selectUserById(id: String): UserEntitiy = queries.selectUserById(id).executeAsOne()
}