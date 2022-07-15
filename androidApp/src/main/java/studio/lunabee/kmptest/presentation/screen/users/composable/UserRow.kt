package studio.lunabee.kmptest.presentation.screen.users.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import studio.lunabee.kmptest.domain.model.User
import studio.lunabee.kmptest.presentation.ui.res.dimens.mediumPadding
import studio.lunabee.kmptest.presentation.ui.res.dimens.smallImageProfile
import studio.lunabee.kmptest.presentation.ui.res.dimens.smallPadding

@Composable
fun UserRow(user: User, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(smallPadding)
    ) {
        AsyncImage(
            model = user.profilePicture,
            contentDescription = null,
            modifier = Modifier
                .size(smallImageProfile)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(mediumPadding))

        Column {
            Text(user.getFullName(), style = MaterialTheme.typography.body1)
            Text(
                user.email,
                style = MaterialTheme.typography.body2.copy(color = Color.Gray),
            )
        }
    }
}

@Preview
@Composable
fun UserRowPreview() {
    val user = User(
        id = "",
        firstName = "Henri",
        lastName = "Paul",
        email = "example@gmaol.com",
        profilePicture = "url-to-image.com"
    )
    UserRow(user = user)
}