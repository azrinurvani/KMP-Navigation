package com.azrinurvani.kmp_bottom_sheet_and_dialog.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TabNavScreen(
    modifier : Modifier = Modifier,
    startTabIndex : Int = 0
){
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Tab 1","Tab 2","Tab 3")
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = tabIndex
        ){
            tabTitles.forEachIndexed { index,title->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(title) }
                )
            }
        }
        when(tabIndex){
            0 -> Text("Content Tab 1",modifier = Modifier.padding(16.dp))
            1 -> Text("Content Tab 2",modifier = Modifier.padding(16.dp))
            2 -> Text("Content Tab 3",modifier = Modifier.padding(16.dp))
        }
    }
}

@Preview
@Composable
fun TabNavScreenPreview(){
    TabNavScreen()
}