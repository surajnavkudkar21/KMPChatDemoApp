package com.example.chatdemoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suraj.kmpchatdemo.model.Message
import com.suraj.kmpchatdemo.model.MessageListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

/**
 * ViewModel for managing the state of the message list in a chat conversation.
 *
 * @param application The Android application context.
 * @param uiState The initial state of the message list UI.
 */
class MessageListViewModel(
    uiState: MutableStateFlow<MessageListUiState> = MutableStateFlow(MessageListUiState())
) : ViewModel() {

    internal val uiState: StateFlow<MessageListUiState> = uiState.asStateFlow()

    /**
     * Posts a new message to the chat conversation.
     *
     * This function adds the given message to the beginning of the message list and
     * simulates a delay before adding a bot response.
     *
     * @param message The [Message] object to be posted.*/
    fun postMessage(message: Message) {
        viewModelScope.launch {
            uiState.value.addMessageAtBeginning(message)
            delay(5000)

            uiState.value.addMessageAtBeginning(
                Message(
                    messageContent = message.messageContent,
                    isFromMe = false,
                    dateTime = getCurrentDateTime()
                )
            )

        }
    }

    fun deleteMessage(id: Long) {
        viewModelScope.launch {
            uiState.value.deleteMessage(id)
        }
    }

    @OptIn(FormatStringsInDatetimeFormats::class)
    fun getCurrentDateTime(): String {
        val format = LocalDateTime.Format { byUnicodePattern("HH:mm") }
        return format.format(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
    }
}