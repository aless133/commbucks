package com.aless133.commbucks.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aless133.commbucks.CommbucksScreen
import com.aless133.commbucks.LocalNavController
import com.aless133.commbucks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    )
{
    val navController = LocalNavController.current
    val currentRoute = navController.currentBackStackEntry?.destination?.route;
    val canNavigateBack = navController.previousBackStackEntry != null

    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
    ), navigationIcon = {
        if (currentRoute == CommbucksScreen.Events.name) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.button_menu),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        } else if (canNavigateBack) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.button_menu),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        } else {
            IconButton(onClick = {
                navController.navigate(CommbucksScreen.Events.name) {
                    popUpTo(CommbucksScreen.Events.name) {
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.button_menu),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }, title = {
        Text(title)
    })
}

