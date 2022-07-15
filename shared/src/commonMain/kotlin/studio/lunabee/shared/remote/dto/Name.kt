package studio.lunabee.shared.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    @SerialName("title")
    val title: String,

    @SerialName("first")
    val first: String,

    @SerialName("last")
    val last: String,
)
