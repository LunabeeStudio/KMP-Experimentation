package studio.lunabee.kmptest.presentation.screen.showNotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.getViewModel
import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.presentation.screen.showNotes.components.NoteCard
import studio.lunabee.kmptest.presentation.theme.dimens.paddingMedium
import studio.lunabee.kmptest.presentation.theme.dimens.spacedBySmall

@Composable
fun ShowNotesScreen(navToCreateNoteScreen: () -> Unit) {

    val viewModel: ShowNotesViewModel = getViewModel()
    val notes by viewModel.notes.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = navToCreateNoteScreen) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.navigate_to_note_screen))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = { TopAppBar(title = { Text(stringResource(R.string.show_notes)) }) },
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(spacedBySmall),
            modifier = Modifier
                .padding(paddingMedium)
                .fillMaxWidth(),
        ) {
            items(notes) { note ->
                NoteCard(
                    title = note.title,
                    body = note.content,
                    removeNote = { viewModel.deleteNote(note) }
                )
            }
        }
    }
}