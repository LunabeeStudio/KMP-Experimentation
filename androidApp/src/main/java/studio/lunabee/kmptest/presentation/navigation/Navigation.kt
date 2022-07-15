package studio.lunabee.kmptest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import studio.lunabee.kmptest.presentation.screen.createNote.CreateNoteScreen
import studio.lunabee.kmptest.presentation.screen.showNotes.ShowNotesScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.ShowNote.route) {
        composable(Destination.ShowNote.route) {
            ShowNotesScreen(
                navToCreateNoteScreen = { navController.navigate(Destination.CreateNote.route) },
            )

        }

        composable(Destination.CreateNote.route) {
            CreateNoteScreen(
                navToShowNotesScreen = { navController.navigate(Destination.ShowNote.route) },
            )
        }
    }
}