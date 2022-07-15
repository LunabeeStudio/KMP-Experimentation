package studio.lunabee.kmptest.screen

sealed class Destination(val route: String) {

    object Email : Destination("email") {
        object Result : Destination("email/{$RESULT_FORMAT_EMAIL}")

        fun navToResult(result: Boolean): String = "${Email.route}/$result"
        const val RESULT_FORMAT_EMAIL: String = "result"
    }

}