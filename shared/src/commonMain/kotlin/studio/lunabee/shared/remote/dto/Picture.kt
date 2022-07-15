package studio.lunabee.shared.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    @SerialName("thumbnail")
    val profile: String,
)
