package studio.lunabee.kmptest.presentation.screen.detail.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.compose.stringResource
import lunabee.studio.resources.MR

@Composable
fun TopBar(navBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(MR.strings.detail_screen_title)) },
        navigationIcon = {
            IconButton(onClick = navBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(MR.strings.detail_screen_top_bar_content_desc_nav_back)
                )
            }
        }
    )
}