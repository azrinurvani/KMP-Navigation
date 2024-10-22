package com.azrinurvani.kmp_bottom_sheet_and_dialog.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogExampleScreen(
    modifier: Modifier = Modifier
){
    var showDialog by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {
                showDialog = true
            }
        ){
            Text(
                text = "Show Dialog"
            )
        }
    }

    if (showDialog){
        Dialog(
            onDismissRequest = {
                showDialog = false
            }
        ){
            DialogContent{
                showDialog = false
            }
        }
    }
}
@Composable
private fun DialogContent(onClose: () -> Unit){
    Surface {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(
                text = "This is a modal dialog",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onClose,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                Text(
                    text = "Close"
                )
            }
        }
    }
}

@Preview
@Composable
fun DialogExampleScreenPreview(){
    DialogExampleScreen()
}

@Preview
@Composable
fun DialogContentPreview(){
    DialogContent(
        onClose = {}
    )
}