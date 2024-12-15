package com.suraj.kmpchatdemo.composeview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.chatdemoapp.ui.theme.Transparent
import kmpchatdemo.composeapp.generated.resources.Res
import kmpchatdemo.composeapp.generated.resources.placeholder_message
import org.jetbrains.compose.resources.stringResource

const val MessageInputViewTestTag = "MessageInputViewTestTag"

/**
 * This composable function provides a text field for users to type their messages and a
 * send button to submit the message.
 *
 * @param modifier An optional [Modifier] to configure the layout and appearance of the view.
 * @param keyboardType The type of keyboard to be displayed for text input. Defaults to [KeyboardType.Text].
 * @param onSendMessageClickListener A callback function that is invoked when the user clicks the send button.
 * It receives the entered message as a string argument.
 */
@Composable
fun MessageInputView(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeHolderText: String = stringResource(Res.string.placeholder_message),
    onSendMessageClickListener: (String) -> Unit
) {
    var messageFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    Row(modifier = modifier.padding(8.dp)) {
        TextField(
            value = messageFieldValue,
            onValueChange = { newText ->
                messageFieldValue = newText
            },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .testTag(MessageInputViewTestTag),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent
            ),
            placeholder = {
                Text(text = placeHolderText)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions {
                if (messageFieldValue.text.isNotBlank()) {
                    onSendMessageClickListener(
                        messageFieldValue.text
                    )
                    messageFieldValue = TextFieldValue()
                }
            },
            maxLines = 1
        )
    }
}