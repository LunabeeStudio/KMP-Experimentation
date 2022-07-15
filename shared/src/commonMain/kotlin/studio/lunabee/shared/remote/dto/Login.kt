package studio.lunabee.shared.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Login(
    @SerialName("uuid")
    val uuid: String,

    )
