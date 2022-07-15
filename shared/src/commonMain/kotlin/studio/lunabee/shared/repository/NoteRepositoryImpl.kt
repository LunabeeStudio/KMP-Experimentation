package studio.lunabee.shared.repository

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import lunabee.studio.sqldelight.NoteDatabase
import lunabee.studio.sqldelight.NoteEntity
import lunabee.studio.sqldelight.NoteQueries
import studio.lunabee.shared.domain.model.Note
import studio.lunabee.shared.domain.model.toModels
import studio.lunabee.shared.domain.repository.NoteRepository

class NoteRepositoryImpl(db: NoteDatabase) : NoteRepository {

    private val queries: NoteQueries = db.noteQueries

    override fun getNotes(): Flow<List<Note>> {
        return queries.getNotes()
            .asFlow()
            .transform { noteEntity: Query<NoteEntity> ->
                emit(noteEntity.executeAsList().toModels())
            }
    }

    override fun deleteNote(note: Note) {
        queries.deleteNote(note.id)
    }

    override fun insertNote(note: Note) {
        queries.insetNote(
            id = note.id,
            title = note.title,
            content = note.content
        )
    }
}