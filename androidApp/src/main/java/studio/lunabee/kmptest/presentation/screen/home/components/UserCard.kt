package studio.lunabee.kmptest.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SyncProblem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.icerock.moko.resources.compose.stringResource
import lunabee.studio.resources.MR
import studio.lunabee.kmptest.presentation.theme.dimens.cardItemSize
import studio.lunabee.kmptest.presentation.theme.dimens.largeCornerShape
import studio.lunabee.kmptest.presentation.theme.dimens.paddingMediumLarge
import studio.lunabee.kmptest.presentation.theme.dimens.smallCornerShape
import studio.lunabee.kmptest.presentation.theme.dimens.smallElevation
import studio.lunabee.shared.common.Result
import studio.lunabee.shared.domain.model.Location
import studio.lunabee.shared.domain.model.User

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCard(result: Result<User>, reload: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        elevation = smallElevation,
        shape = RoundedCornerShape(largeCornerShape),
        onClick = reload,
        modifier = modifier
    ) {
        when (result) {

            is Result.Loading -> Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            is Result.Error -> Box(contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.SyncProblem,
                        contentDescription = stringResource(MR.strings.home_screen_content_desc_error_loading_profile),
                        tint = MaterialTheme.colors.error,
                        modifier = Modifier.size(cardItemSize / 2)
                    )
                    Text(
                        text = stringResource(MR.strings.home_screen_error_image_reload),
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center
                    )
                }
            }

            is Result.Success -> Box(modifier = Modifier.size(cardItemSize)) {
                val user = result.data!!
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.profilePicture)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(RoundedCornerShape(smallCornerShape))
                        .size(cardItemSize)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.DarkGray
                                ),
                                startY = 450f
                            )
                        )
                )

                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingMediumLarge)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "${user.first}, ",
                                style = MaterialTheme.typography.subtitle1.copy(Color.White, fontWeight = FontWeight.Bold)
                            )
                            Text(text = user.age.toString(), style = MaterialTheme.typography.body2.copy(Color.White))

                        }
                        Text(
                            "${user.location.city}, ${user.location.country}",
                            style = MaterialTheme.typography.body2.copy(Color.White, fontWeight = FontWeight.Light)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    val user: Result<User> = Result.Success(
        User(
            first = "Paul",
            last = "Jean",
            nationality = "FR",
            age = 45,
            profilePicture = "",
            location = Location(
                country = "France",
                city = "Lyon"
            ),
            id = ""
        )
    )
    UserCard(user, {})
}