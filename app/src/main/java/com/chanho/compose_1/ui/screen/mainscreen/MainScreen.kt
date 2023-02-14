package com.chanho.compose_1.ui.screen.mainscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chanho.compose_1.R
import com.chanho.compose_1.navigation.Navigation
import com.chanho.compose_1.navigation.Screen
import com.chanho.compose_1.ui.component.CircularIndeterminateProgressBar
import com.chanho.compose_1.ui.theme.Green200

@Composable
fun MainScreen(context: Context) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val searchProgressBar = remember { mutableStateOf(false) }
    val appBartitle = remember { mutableStateOf(R.string.app_title) }

    LaunchedEffect(true) {
        mainViewModel.genreList()
    }

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            if (isAppBarVisible.value) {
                val appTitle: String = stringResource(id = appBartitle.value)
                HomeAppBar(appTitle, openDrawer = {}, context = context)
            } else {

            }
        }, bottomBar = {
            when (currentRout(navController = navController)) {
                else -> {
                    BottomNavigationUI(navController,appBartitle)
                }
            }
        }, content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Navigation(navController, Modifier.padding(it))
                Column {
                    CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
                }
            }

        })
}

@Composable
fun BottomNavigationUI(navController: NavController,appBartitle: MutableState<Int>) {
    BottomNavigation {
        val items = listOf(
            Screen.HomeNav,
            Screen.TopRatedNav
        )

        items.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = stringResource(id = item.title)) },
                icon = item.navIcon,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                selected = currentRout(navController = navController) == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            appBartitle.value= item.title
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun HomeAppBar(title: String, openDrawer: () -> Unit,context: Context) {
    TopAppBar(
        backgroundColor = Green200,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Menu, "menu")
            }
        })
}

@Composable
fun currentRout(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}