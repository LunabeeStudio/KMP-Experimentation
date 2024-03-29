package studio.lunabee.kmptest.presentation.navigation

sealed class Destination(val route: String) {

    object Home : Destination("Home")

    object Detail : Destination("Detail")

}
