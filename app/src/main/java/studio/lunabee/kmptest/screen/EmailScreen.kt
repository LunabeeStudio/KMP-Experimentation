package studio.lunabee.kmptest.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.ui.res.dimens.cornerRadius
import studio.lunabee.kmptest.ui.res.dimens.padding
import studio.lunabee.kmptest.ui.res.dimens.smallSpacer
import studio.lunabee.kmptest.ui.theme.KMPTestTheme

@Composable
fun EmailScreen(email: String, emailChange: (String) -> Unit, onValidate: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier.clip(RoundedCornerShape(cornerRadius)),
        ) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(padding)) {
                Text(stringResource(R.string.enter_email_address))
                Spacer(modifier = Modifier.height(smallSpacer))
                TextField(value = email, onValueChange = emailChange, singleLine = true)
                Button(onClick = onValidate) {
                    Text(text = stringResource(R.string.validate))
                }
            }
        }
    }
}

@Preview
@Composable
fun EmailScreenPreview() {
    KMPTestTheme {
        EmailScreen("as@as.com", {}, {})
    }
}