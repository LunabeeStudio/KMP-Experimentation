package studio.lunabee.kmptest.presentation.navigation

sealed class Destination(val route: String) {
    object ShowNote : Destination("showNotes")
    object CreateNote : Destination("createNote")
}
