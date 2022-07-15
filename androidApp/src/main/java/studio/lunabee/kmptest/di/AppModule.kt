package studio.lunabee.kmptest.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.kmptest.presentation.screen.createNote.CreateNoteViewModel
import studio.lunabee.kmptest.presentation.screen.showNotes.ShowNotesViewModel

val appModule: Module = module {
    viewModel { ShowNotesViewModel(get()) }
    viewModel { CreateNoteViewModel(get()) }
}