package studio.lunabee.kmptest.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import studio.lunabee.kmptest.R

@Composable
fun HomeScreen(navToUsersScreen: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.screen_home_title)) },
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(
                onClick = navToUsersScreen,
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(text = stringResource(R.string.fetch_data))
            }
        }
    }
}