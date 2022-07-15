package studio.lunabee.kmptest.domain.model

import studio.lunabee.shared.remote.dto.UserDto

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val profilePicture: String,
) {
    fun getFullName(): String = "${lastName.uppercase()} $firstName"
}

fun UserDto.toModel(): User {
    return User(
        id = login.uuid,
        firstName = name.first,
        lastName = name.last,
        email = email,
        profilePicture = picture.profile
    )
}
