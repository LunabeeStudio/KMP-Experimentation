package studio.lunabee.kmptest.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.ui.theme.KMPTestTheme

@Composable
fun ResultScreen(result: Boolean, navBack: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(if (result) stringResource(R.string.email_correct_format) else stringResource(R.string.email_incorrect_format))
        Button(onClick = navBack) {
            Text(text = stringResource(R.string.back))
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    KMPTestTheme {
        ResultScreen(result = true, {})
    }
}