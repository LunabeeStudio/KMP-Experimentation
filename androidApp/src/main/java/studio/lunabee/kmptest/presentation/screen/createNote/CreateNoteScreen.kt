package studio.lunabee.kmptest.presentation.screen.createNote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.getViewModel
import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.presentation.theme.dimens.paddingLarge
import studio.lunabee.kmptest.presentation.theme.dimens.spacedByMedium
import studio.lunabee.shared.domain.model.Note
import java.util.UUID

@Composable
fun CreateNoteScreen(navToShowNotesScreen: () -> Unit) {

    val viewModel: CreateNoteViewModel = getViewModel()

    val (title, setTitle) = remember { mutableStateOf("") }
    val (content, setContent) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navToShowNotesScreen) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.create_note_content_desc_go_to_notes_screen),
                        )
                    }
                },
                title = { Text(stringResource(R.string.screen_create_note)) }
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(paddingLarge),
            verticalArrangement = Arrangement.spacedBy(spacedByMedium)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = setTitle,
                label = { Text(text = stringResource(R.string.create_note_title_field)) },
            )
            OutlinedTextField(
                value = content,
                onValueChange = setContent,
                label = { Text(text = stringResource(R.string.create_note_content_field)) },
            )
            Button(onClick = {
                viewModel.insertNewNote(
                    Note(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        content = content
                    )
                )
                navToShowNotesScreen()
            }) {
                Text(text = stringResource(R.string.create_note_button))
            }
        }
    }
}
