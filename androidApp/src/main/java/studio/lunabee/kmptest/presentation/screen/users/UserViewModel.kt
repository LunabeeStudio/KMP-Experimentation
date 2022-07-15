package studio.lunabee.kmptest.presentation.screen.users

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import studio.lunabee.kmptest.domain.result.Result
import studio.lunabee.kmptest.domain.use_case.FetchUsers

class UserViewModel(
    private val fetchUsers: FetchUsers,
) : ViewModel() {

    private var _usersList: MutableState<UsersListState> = mutableStateOf(UsersListState())
    var usersList: State<UsersListState> = _usersList

    init {
        getUsersList()
    }

    private fun getUsersList() {
        fetchUsers().onEach { result ->
            when (result) {
                is Result.Success -> _usersList.value = UsersListState(users = result.data!!)
                is Result.Error -> _usersList.value = UsersListState(error = result.message!!)
                is Result.Loading -> _usersList.value = UsersListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}