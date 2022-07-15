package studio.lunabee.kmptest.presentation.screen.showNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import studio.lunabee.shared.domain.model.Note
import studio.lunabee.shared.domain.repository.NoteRepository

class ShowNotesViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val notes: Flow<List<Note>> = noteRepository.getNotes()

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

}