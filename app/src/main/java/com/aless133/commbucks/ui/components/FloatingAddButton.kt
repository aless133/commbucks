package com.aless133.commbucks.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.aless133.commbucks.R

@Composable
fun FloatingAddButton(
    onClick: ()->Unit = { },
    iconImage: ImageVector = Icons.Filled.Add,
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        onClick = onClick,
        shape = CircleShape,
    ) {
        Icon(iconImage, stringResource(R.string.button_add))
    }
}