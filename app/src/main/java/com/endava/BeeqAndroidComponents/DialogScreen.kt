package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import endava.beeq.compose.organisms.BeeqDialog
import endava.beeq.compose.organisms.button.BeeqButton
import endava.beeq.compose.organisms.button.BeeqButtonColors
import endava.beeq.compose.organisms.button.ButtonAction
import endava.beeq.compose.organisms.button.ButtonStyle

@Composable
fun DialogScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var showDialog3 by remember { mutableStateOf(false) }

    val icon = rememberVectorPainter(image = Icons.Default.Lock)

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {

        SectionHeader("Dialog")

        BeeqButton(
            onClick = ButtonAction.OnClick {
                showDialog = true
            },
            text = "show dialog without footer",
            style = ButtonStyle.NormalButton(colorsStyle = BeeqButtonColors.Primary)
        )

        BeeqButton(
            onClick = ButtonAction.OnClick {
                showDialog2 = true
            },
            text = "show dialog with normal footer",
            style = ButtonStyle.NormalButton(colorsStyle = BeeqButtonColors.Primary)
        )

        BeeqButton(
            onClick = ButtonAction.OnClick {
                showDialog3 = true
            },
            text = "show dialog with highlighted footer",
            style = ButtonStyle.NormalButton(colorsStyle = BeeqButtonColors.Primary)
        )

        if (showDialog) {
            BeeqDialog(
                icon = icon,
                onDismissRequest = { showDialog = false },
                title = "Confirm action",
                highlightFooter = false,
                content = {
                    Text("Are you sure you want to proceed?")
                },
            )
        }

        if (showDialog2) {
            BeeqDialog(
                onDismissRequest = { showDialog2 = false },
                title = "Confirm action",
                icon = icon,
                highlightFooter = false,
                content = {
                    Text("Are you sure you want to proceed?")
                },
                dismissOnClickOutside = false,
                footer = {
                    TextButton(onClick = { showDialog2 = false }) {
                        Text("Cancel")
                    }
                    Button(onClick = { /* Confirm action */ }) {
                        Text("Confirm")
                    }
                }
            )
        }

        if (showDialog3) {
            BeeqDialog(
                onDismissRequest = { showDialog3 = false },
                title = "Confirm action",
                highlightFooter = true,
                content = {
                    Text("Are you sure you want to proceed?")
                },
                footer = {
                    Button(onClick = { /* Confirm action */ }) {
                        Text("Confirm")
                    }
                }
            )
        }

    }
}