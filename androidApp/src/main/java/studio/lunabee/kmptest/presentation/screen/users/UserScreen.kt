package studio.lunabee.kmptest.presentation.screen.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import org.koin.androidx.compose.viewModel
import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.presentation.screen.users.composable.UsersList

@Composable
fun UsersScreen(
    backNav: () -> Unit,
) {
    val viewModel: UserViewModel by viewModel()

    val listState: UsersListState = viewModel.usersList.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.screen_user_title)) },
                navigationIcon = {
                    IconButton(onClick = backNav) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.navigate_previous_screen))
                    }
                }
            )
        }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                listState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                listState.error.isNotBlank() -> {
                    Text(
                        text = listState.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    )
                }
                else -> UsersList(users = listState.users, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
