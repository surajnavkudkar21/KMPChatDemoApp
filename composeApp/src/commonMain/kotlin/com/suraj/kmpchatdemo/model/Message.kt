package com.suraj.kmpchatdemo.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * Data class representing a chat message.
 *
 * @property messageContent The actual text content of the message.
 * @property isFromMe A boolean value indicating whether the message was sent by the user (`true`) or received (`false`).
 * @property dateTime The timestamp of the message, formatted as "hh:mm" (hours and minutes).
 */
data class Message(
    val messageContent: String,
    val isFromMe: Boolean,
    val dateTime: String ,
    val id : Long = Clock.System.now().toEpochMilliseconds()
)
