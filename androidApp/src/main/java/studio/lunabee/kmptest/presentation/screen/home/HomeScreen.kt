package studio.lunabee.kmptest.presentation.screen.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import lunabee.studio.resources.MR
import org.koin.androidx.compose.getViewModel
import studio.lunabee.kmptest.presentation.screen.home.components.ActionBar
import studio.lunabee.kmptest.presentation.screen.home.components.TopBar
import studio.lunabee.kmptest.presentation.screen.home.components.UserCard
import studio.lunabee.kmptest.presentation.theme.dimens.cardItemSize
import studio.lunabee.kmptest.presentation.theme.dimens.spacedByExtraLarge
import studio.lunabee.shared.common.Like
import studio.lunabee.shared.common.Result
import studio.lunabee.shared.domain.model.User
import studio.lunabee.shared.remote.ErrorException

@Composable
fun HomeScreen(navToDetailScreen: () -> Unit) {

    val viewModel: HomeViewModel = getViewModel()

    val userState: State<Result<User>> = viewModel.result

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { TopBar(navToDetailScreen) },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(0.8f)
            ) {
                UserCard(
                    result = userState.value,
                    reload = { viewModel.getUser() },
                    modifier = Modifier.size(cardItemSize)
                )

                Spacer(modifier = Modifier.height(spacedByExtraLarge))

                ActionBar { like: Like ->
                    if (userState.value is Result.Success) {
                        val user = userState.value

                        user.data!!.liked = like.value

                        viewModel.updateUser(user.data!!)
                    }
                }
            }
            if (userState.value is Result.Error) {
                val msg: String = when (userState.value.ex!!) {
                    is ErrorException.Network -> stringResource(MR.strings.error_network_connexion)
                    is ErrorException.Server -> stringResource(MR.strings.error_network_server)
                    is ErrorException.Unexpected -> stringResource(MR.strings.error_unexpected)
                }
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(width = 2.dp, color = Color.Cyan)
                ) {
                    LaunchedEffect(userState.value) {
                        scope.launch {
                            snackbarHostState.showSnackbar(msg, duration = SnackbarDuration.Long)
                        }
                    }
                    SnackbarHost(hostState = snackbarHostState)

                }
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navToDetailScreen = {})
}