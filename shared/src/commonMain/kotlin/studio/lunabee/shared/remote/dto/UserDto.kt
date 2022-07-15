package studio.lunabee.shared.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("name")
    val name: Name,

    @SerialName("email")
    val email: String,

    @SerialName("picture")
    val picture: Picture,

    @SerialName("login")
    val login: Login,
)


