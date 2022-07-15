package studio.lunabee.kmptest.presentation.screen.users

import studio.lunabee.kmptest.domain.model.User

data class UsersListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = "",
)
