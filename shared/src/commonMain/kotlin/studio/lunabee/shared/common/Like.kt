package studio.lunabee.shared.common

sealed class Like(val value: Int) {
    object No : Like(value = -1)

    object Undefined : Like(value = 0)

    object Yes : Like(value = 1)
}