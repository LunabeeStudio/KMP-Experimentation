package studio.lunabee.kmptest.presentation.screen.showNotes.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import studio.lunabee.kmptest.R
import studio.lunabee.kmptest.presentation.theme.dimens.smallElevation

@Composable
fun NoteCard(title: String, body: String, removeNote: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = smallElevation,
        modifier = Modifier.animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                )
                IconButton(onClick = removeNote) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = stringResource(R.string.delete_note))
                }
            }
            if (isExpanded) {
                Text(text = body, maxLines = if (isExpanded) Int.MAX_VALUE else 1)
            }
        }
    }
}

@Preview
@Composable
fun NoteCardPreview() {
    NoteCard("Title", "This a short content") {}
}