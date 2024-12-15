package com.suraj.kmpchatdemo.composeview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.chatdemoapp.ui.viewmodel.MessageListViewModel
import com.suraj.kmpchatdemo.model.Message
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kmpchatdemo.composeapp.generated.resources.Res
import kmpchatdemo.composeapp.generated.resources.app_name
import kmpchatdemo.composeapp.generated.resources.placeholder_message
import org.jetbrains.compose.resources.stringResource

/**
 * Parent view for the chat screen, serving as the entry point.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param messageListViewModel ViewModel for managing the message list state.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenParentView(
    modifier: Modifier = Modifier, messageListViewModel: MessageListViewModel = viewModel {
        MessageListViewModel()
    }
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val uiState by messageListViewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()


    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        TopCenterAlignedAppBar(
            title = stringResource(Res.string.app_name),
            scrollBehavior = scrollBehavior
        )
    }) { innerPadding ->
        Column(
            Modifier.fillMaxSize().padding(innerPadding)
        ) {
            // View for Message list
            MessageListView(messageList = uiState.messageList,
                scrollState = scrollState,
                modifier = Modifier.weight(1f),
                onClick = { message ->
                    messageListViewModel.deleteMessage(message.id)
                }

            )

            // View to input chat message
            MessageInputView { message ->
                messageListViewModel.postMessage(
                    Message(
                        messageContent = message,
                        isFromMe = true,
                        dateTime = messageListViewModel.getCurrentDateTime()
                    )
                )
            }
        }

    }
}