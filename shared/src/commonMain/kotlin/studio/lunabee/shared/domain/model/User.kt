package studio.lunabee.shared.domain.model

import lunabee.studio.sqldelight.UserEntitiy
import studio.lunabee.shared.common.Like
import studio.lunabee.shared.remote.dto.UserDto

data class User(
    val id: String,

    val profilePicture: String,

    val first: String,
    val last: String,

    val age: Int,

    val location: Location,
    val nationality: String,

    var liked: Int = Like.Undefined.value,
)

data class Location(
    val country: String,
    val city: String,
)

fun User.toEntity(): UserEntitiy = UserEntitiy(
    id = id,
    profilePicture = profilePicture,
    firstName = first,
    lastName = last,
    age = age,
    city = location.city,
    country = location.country,
    nationality = nationality,
    liked = liked,
)

fun UserEntitiy.toModel(): User = User(
    id = id,
    profilePicture = profilePicture,
    first = firstName,
    last = lastName,
    age = age,
    location = Location(
        country = country,
        city = city,
    ),
    nationality = nationality,
    liked = liked,
)

fun UserDto.toModel(): User = User(
    id = login.uuid,
    profilePicture = picture.large,
    first = name.first,
    last = name.last,
    age = dob.age,
    location = Location(
        country = location.country,
        city = location.city,
    ),
    nationality = nationality,
    liked = 0
)

fun List<UserEntitiy>.localToModel(): List<User> = this.map { it.toModel() }

fun List<UserDto>.remoteToModel(): List<User> = this.map { it.toModel() }

