package studio.lunabee.shared.domain.repository

import kotlinx.coroutines.flow.Flow
import studio.lunabee.shared.domain.model.Note

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    fun deleteNote(note: Note)

    fun insertNote(note: Note)

}