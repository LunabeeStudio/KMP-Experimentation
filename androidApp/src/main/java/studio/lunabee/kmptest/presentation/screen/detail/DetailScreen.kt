package studio.lunabee.kmptest.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.compose.stringResource
import lunabee.studio.resources.MR
import org.koin.androidx.compose.getViewModel
import studio.lunabee.kmptest.presentation.screen.detail.components.RowUser
import studio.lunabee.kmptest.presentation.screen.detail.components.TopBar
import studio.lunabee.kmptest.presentation.theme.dimens.paddingMedium
import studio.lunabee.kmptest.presentation.theme.dimens.paddingMediumLarge
import studio.lunabee.shared.domain.model.User

@Composable
fun DetailScreen(navToHomeScreen: () -> Unit) {

    val viewModel: DetailViewModel = getViewModel()

    var selectedIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        stringResource(MR.strings.detail_screen_tab_like),
        stringResource(MR.strings.detail_screen_tab_dislike)
    )

    Scaffold(topBar = { TopBar(navToHomeScreen) }) {
        Column(Modifier.fillMaxSize()) {
            TabRow(
                selectedTabIndex = selectedIndex,
                backgroundColor = Color(0xFF2E5894),
                contentColor = Color(0xFFBCD4E6)
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                    ) {
                        Text(
                            text = tab,
                            modifier = Modifier.padding(paddingMediumLarge)
                        )
                    }
                }
            }

            val list: State<List<User>> = if (selectedIndex == 0) {
                viewModel.likedUsers.collectAsState(initial = emptyList())
            } else {
                viewModel.dislikedUsers.collectAsState(initial = emptyList())
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingMedium)
            ) {
                items(list.value.size) { index: Int ->
                    val user = list.value[index]
                    RowUser(image = user.profilePicture, first = user.first, last = user.last)
                }
            }
        }
    }

}