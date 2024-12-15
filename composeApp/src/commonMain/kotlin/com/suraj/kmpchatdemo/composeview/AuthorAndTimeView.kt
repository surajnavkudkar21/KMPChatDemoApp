package com.suraj.kmpchatdemo.composeview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.dp
import com.suraj.kmpchatdemo.model.Message
import kmpchatdemo.composeapp.generated.resources.Res
import kmpchatdemo.composeapp.generated.resources.author_bot
import kmpchatdemo.composeapp.generated.resources.author_me
import org.jetbrains.compose.resources.stringResource

/**
 * This composable function renders the author's name and the timestamp associated with
 * a chat message.
 *
 * @param modifier An optional [Modifier] to configure the layout and appearance of the view.
 * @param message The [Message] object containing the author and timestamp information.
 */
@Composable
fun AuthorAndTimeView(
    modifier: Modifier = Modifier,
    message: Message
) {
    val authorMe = stringResource(Res.string.author_me)
    val authorBot = stringResource(Res.string.author_bot)
    Row(
        modifier = modifier
    ) {
        Text(
            text = if (message.isFromMe) authorMe else authorBot,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = message.dateTime,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.alignBy(LastBaseline),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}