package com.azrinurvani.kmp_bottom_sheet_and_dialog.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ModalBottomSheetContent(onClose: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 16.dp,
            bottom = 32.dp,
            start = 16.dp,
            end = 16.dp
        )) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = onClose) {
            Text("Hide bottom sheet")
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun ModalBottomSheetContentPreview() {
    ModalBottomSheetContent {

    }
}

