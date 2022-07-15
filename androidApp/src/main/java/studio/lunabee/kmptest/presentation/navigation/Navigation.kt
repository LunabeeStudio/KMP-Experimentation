package studio.lunabee.kmptest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import studio.lunabee.kmptest.presentation.screen.detail.DetailScreen
import studio.lunabee.kmptest.presentation.screen.home.HomeScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) {
            HomeScreen(navToDetailScreen = { navController.navigate(Destination.Detail.route) })
        }

        composable(Destination.Detail.route) {
            DetailScreen(navToHomeScreen = { if (!navController.popBackStack()) navController.navigate(Destination.Home.route) })
        }

    }
}