package studio.lunabee.kmptest.presentation.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import studio.lunabee.shared.common.Result
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.domain.repository.UserRepository
import studio.lunabee.shared.domain.use_case.GetNewUser
import kotlin.random.Random

class HomeViewModel(
    private val userRepository: UserRepository,
    private val GetNewUser: GetNewUser,
) : ViewModel() {

    var result: MutableState<Result<User>> = mutableStateOf(Result.Loading())
        private set

    init {
        getUser()
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
            result.value = Result.Loading()
            result.value = GetNewUser(page = Random.nextInt(0, 499))
        }
    }

    fun getUser() {
        result.value = Result.Loading()
        viewModelScope.launch {
            var tmp: Result<User>

            withContext(Dispatchers.IO) {
                tmp = GetNewUser(page = Random.nextInt(0, 499))

            }
            result.value = tmp
        }
    }

}

