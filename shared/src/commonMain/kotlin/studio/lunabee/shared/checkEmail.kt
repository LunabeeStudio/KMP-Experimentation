package studio.lunabee.shared

private val emailRegex = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+").toRegex()

fun String.checkEmail(): Boolean {
    return emailRegex.matches(this)
}