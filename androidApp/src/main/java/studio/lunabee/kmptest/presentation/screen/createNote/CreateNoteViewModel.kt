package studio.lunabee.kmptest.presentation.screen.createNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import studio.lunabee.shared.domain.model.Note
import studio.lunabee.shared.domain.repository.NoteRepository

class CreateNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    fun insertNewNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

}