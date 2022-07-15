package studio.lunabee.kmptest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import studio.lunabee.kmptest.presentation.navigation.NavGraph
import studio.lunabee.kmptest.presentation.ui.theme.KMPTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KMPTestTheme {
                NavGraph()
            }
        }
    }
}