package studio.lunabee.shared.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("name")
    val name: Name,

    @SerialName("location")
    val location: Location,

    @SerialName("login")
    val login: Login,

    @SerialName("dob")
    val dob: Dob,

    @SerialName("picture")
    val picture: Picture,

    @SerialName("nat")
    val nationality: String,
)

@Serializable
data class Name(
    val first: String,
    val last: String,
)

@Serializable
data class Location(
    val city: String,
    val country: String,
)

@Serializable
data class Login(
    @SerialName("uuid")
    val uuid: String,
)

@Serializable
data class Dob(
    val age: Int,
)

@Serializable
data class Picture(
    val large: String,
)