package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import endava.beeq.compose.organisms.BeeqDivider
import endava.beeq.compose.theme.Colors


@Composable
fun DividerScreen() {

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {

        SectionHeader("Divider")

        BeeqDivider(
            orientation = Orientation.Vertical,
            dashed = false,
            strokeColor = Colors.icon.primary,
            modifier = Modifier.height(100.dp)
        )

        BeeqDivider(
            title = "OR",
            dashed = true,
            strokeColor = Color.Red,
            strokeDashGap = 6.dp,
            titleAlignment = Alignment.CenterHorizontally
        )

        BeeqDivider(
            title = "End",
            titleAlignment = Alignment.End,
            strokeColor = MaterialTheme.colorScheme.primary
        )
    }
}