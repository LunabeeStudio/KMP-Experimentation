package studio.lunabee.kmptest.presentation.screen.users.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import studio.lunabee.kmptest.domain.model.User
import studio.lunabee.kmptest.presentation.ui.res.dimens.mediumPadding

@Composable
fun UsersList(users: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            items = users,
            key = { (id) -> id },
        ) { user ->
            UserRow(
                user = user, modifier = Modifier
                .padding(mediumPadding)
                .semantics(mergeDescendants = true) {}
            )
            Divider(color = Color.Gray)
        }
    }
}