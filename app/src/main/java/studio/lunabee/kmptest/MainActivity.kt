package studio.lunabee.kmptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import studio.lunabee.kmptest.screen.Destination
import studio.lunabee.kmptest.screen.EmailScreen
import studio.lunabee.kmptest.screen.ResultScreen
import studio.lunabee.kmptest.ui.theme.KMPTestTheme
import studio.lunabee.shared.checkEmail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KMPTestTheme {
                val (email, emailChange) = remember { mutableStateOf("") }
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Destination.Email.route) {
                    composable(Destination.Email.route) {
                        EmailScreen(email, emailChange) {
                            navController.navigate(
                                Destination.Email.navToResult(email.checkEmail())
                            )
                        }
                    }

                    composable(
                        Destination.Email.Result.route,
                        arguments = listOf(
                            navArgument(Destination.Email.RESULT_FORMAT_EMAIL) {
                                type = NavType.BoolType
                            },
                        )
                    ) {
                        it.arguments?.getBoolean(Destination.Email.RESULT_FORMAT_EMAIL)?.let { it1 ->
                            ResultScreen(result = it1, navBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}


