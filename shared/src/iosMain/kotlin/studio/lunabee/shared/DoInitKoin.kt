package studio.lunabee.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import studio.lunabee.shared.domain.repository.NoteRepository

object KoinHelper : KoinComponent {
    val noteRepositoryImpl: NoteRepository by inject()
}