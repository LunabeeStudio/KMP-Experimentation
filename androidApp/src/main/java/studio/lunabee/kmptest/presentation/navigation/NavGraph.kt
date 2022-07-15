package studio.lunabee.kmptest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import studio.lunabee.kmptest.presentation.screen.home.HomeScreen
import studio.lunabee.kmptest.presentation.screen.users.UsersScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) {
            HomeScreen { navController.navigate(Destination.Users.route) }
        }
        composable(Destination.Users.route) {
            UsersScreen(backNav = { navController.popBackStack() })
        }
    }
}