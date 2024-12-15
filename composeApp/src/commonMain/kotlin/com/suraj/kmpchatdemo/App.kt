package com.suraj.kmpchatdemo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.suraj.kmpchatdemo.composeview.ChatScreenParentView
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ChatScreenParentView()
    }
}
