package studio.lunabee.kmptest.presentation.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import studio.lunabee.kmptest.presentation.theme.dimens.fontSize
import studio.lunabee.kmptest.presentation.theme.dimens.largeCornerShape
import studio.lunabee.kmptest.presentation.theme.dimens.largeImageSize
import studio.lunabee.kmptest.presentation.theme.dimens.mediumCornerShape
import studio.lunabee.kmptest.presentation.theme.dimens.mediumElevation
import studio.lunabee.kmptest.presentation.theme.dimens.paddingMediumLarge
import studio.lunabee.kmptest.presentation.theme.dimens.paddingSmall
import studio.lunabee.kmptest.presentation.theme.dimens.smallCornerShape

@Composable
fun RowUser(
    image: String,
    first: String,
    last: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(paddingSmall)
            .fillMaxWidth(),
        shape = RoundedCornerShape(largeCornerShape),
        elevation = mediumElevation
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(smallCornerShape))
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier.size(largeImageSize),
                shape = RoundedCornerShape(mediumCornerShape),
                color = MaterialTheme.colors.surface.copy(alpha = 0.2f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(mediumCornerShape))
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = paddingMediumLarge)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "${last.uppercase()} $first",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = fontSize),
                    color = Color.Black
                )
            }
        }
    }
}