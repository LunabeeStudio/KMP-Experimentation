package studio.lunabee.kmptest.presentation.screen.home.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import studio.lunabee.kmptest.presentation.theme.dimens.extraSmallElevation

@Composable
fun Action(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(elevation = extraSmallElevation, shape = CircleShape, modifier = modifier) {
        content()
    }
}