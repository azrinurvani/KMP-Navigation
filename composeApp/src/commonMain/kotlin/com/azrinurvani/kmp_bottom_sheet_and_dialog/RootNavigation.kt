import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.azrinurvani.kmp_bottom_sheet_and_dialog.arguments.addArgumentExampleScreen
import com.azrinurvani.kmp_bottom_sheet_and_dialog.components.BottomNavigationBarContent
import com.azrinurvani.kmp_bottom_sheet_and_dialog.components.DrawerContent
import com.azrinurvani.kmp_bottom_sheet_and_dialog.components.ModalBottomSheetContent
import com.azrinurvani.kmp_bottom_sheet_and_dialog.dialog.DialogExampleScreen
import com.azrinurvani.kmp_bottom_sheet_and_dialog.tab.TabNavScreen
import kotlinx.coroutines.launch

sealed class RootScreen(var route: String, val icon: ImageVector?, var title: String) {
    data object TabNavExample : RootScreen("tab_nav", Icons.Rounded.Home, "Tabs")
    data object DialogExample : RootScreen("dialog", Icons.AutoMirrored.Rounded.List, "Dialog")
    data object Arguments : RootScreen("arguments", Icons.Rounded.Info, "Arguments")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigation(
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()

    // Drawer Menu
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Bottom Sheet
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var fabVisible by remember { mutableStateOf(true) }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                modifier = Modifier.fillMaxWidth(0.75f)
            ) { scope.launch { drawerState.close() } }
        },
        scrimColor = Color.Black.copy(alpha = 0.32f),
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("App Navigation") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigationBarContent(
                    navController = navController,
                ) { item ->
                    fabVisible = item == RootScreen.TabNavExample
                }
            },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = fabVisible,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally(),
                ) {
                    ExtendedFloatingActionButton(
                        text = { Text("Show bottom sheet") },
                        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                        onClick = {
                            showBottomSheet = true
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(navController, startDestination = RootScreen.TabNavExample.route) {
                addTabNavExampleScreen(Modifier.padding(innerPadding))
                addDialogExampleScreen(Modifier.padding(innerPadding))
                addArgumentExampleScreen(Modifier.padding(innerPadding), navController)
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                ModalBottomSheetContent {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.addTabNavExampleScreen(modifier: Modifier = Modifier) {
    composable(RootScreen.TabNavExample.route) {
        TabNavScreen(modifier)
    }
}

private fun NavGraphBuilder.addDialogExampleScreen(modifier: Modifier = Modifier) {
    composable(RootScreen.DialogExample.route) {
        DialogExampleScreen(modifier)
    }
}
            