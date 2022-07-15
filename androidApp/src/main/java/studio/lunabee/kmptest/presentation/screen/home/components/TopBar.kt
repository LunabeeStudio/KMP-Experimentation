package studio.lunabee.kmptest.presentation.screen.home.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.compose.stringResource
import lunabee.studio.resources.MR

@Composable
fun TopBar(navToDetailScreen: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(MR.strings.home_screen_title)) },
        actions = {
            IconButton(onClick = navToDetailScreen) {
                Icon(imageVector = Icons.Default.Info, contentDescription = stringResource(MR.strings.home_screen_content_desc_nav_detail))
            }
        }
    )
}