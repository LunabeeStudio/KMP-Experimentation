package studio.lunabee.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module
import studio.lunabee.shared.domain.repository.NoteRepository
import studio.lunabee.shared.repository.NoteRepositoryImpl

val commonModule: Module = module {

    single<NoteRepository> { NoteRepositoryImpl(get()) }

}