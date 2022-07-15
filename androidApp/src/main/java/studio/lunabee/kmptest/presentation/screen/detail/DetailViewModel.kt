package studio.lunabee.kmptest.presentation.screen.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.domain.repository.UserRepository

class DetailViewModel(userRepository: UserRepository) : ViewModel() {

    val likedUsers: Flow<List<User>> = userRepository.getLikedUsers()
    val dislikedUsers: Flow<List<User>> = userRepository.getDislikedUsers()

}

