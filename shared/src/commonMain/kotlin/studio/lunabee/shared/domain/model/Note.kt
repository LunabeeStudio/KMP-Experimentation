package studio.lunabee.shared.domain.model

import lunabee.studio.sqldelight.NoteEntity

data class Note(
    val id: String,
    val title: String,
    val content: String,
)

fun NoteEntity.toModel(): Note = Note(
    id = id,
    title = title,
    content = content
)

fun List<NoteEntity>.toModels(): List<Note> = this.map { it.toModel() }