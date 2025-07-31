package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import endava.beeq.compose.organisms.button.BeeqButton
import endava.beeq.compose.organisms.button.BeeqButtonColors
import endava.beeq.compose.organisms.button.ButtonAction
import endava.beeq.compose.organisms.button.ButtonSize
import endava.beeq.compose.organisms.button.ButtonStyle
import kotlinx.coroutines.delay

@Composable
fun ButtonScreen() {
    Column {
        SectionHeader("Button")

        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = { suspendFun() }
                ),
                text = "Primary",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.Person,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Primary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = ButtonAction.OnClick(),
                text = "Primary disable ",
                buttonSize = ButtonSize.Small,
                enabled = false,
                startIcon = Icons.Default.Person,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Primary)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = { suspendFun() }
                ),
                text = "Danger",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Search,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Danger)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                text = "Danger disable",
                buttonSize = ButtonSize.Small,
                enabled = false,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Search,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Danger)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = { suspendFun() }
                ),
                text = "Secondary",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.MailOutline,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Secondary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                text = "Secondary disable",
                buttonSize = ButtonSize.Small,
                enabled = false,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.MailOutline,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Secondary)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(onClick = {}),
                text = "GhostButton Primary",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                endIcon = Icons.Default.Warning,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Primary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                text = "GhostButton Primary",
                buttonSize = ButtonSize.Small,
                enabled = false,
                endIcon = Icons.Default.Warning,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Primary)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = { suspendFun() }
                ),
                text = "GhostButton Secondary",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Secondary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                text = "GhostButton Secondary",
                buttonSize = ButtonSize.Small,
                enabled = false,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Secondary)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = { suspendFun() }
                ),
                text = "Ghost Danger",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Danger)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                text = "Ghost Danger ",
                buttonSize = ButtonSize.Small,
                enabled = false,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Danger)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = { suspendFun() }
                ),
                text = "Ghost Success",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                endIcon = Icons.Default.AddShoppingCart,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Success)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                text = "Ghost Success disable",
                buttonSize = ButtonSize.Small,
                enabled = false,
                endIcon = Icons.Default.AddShoppingCart,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Success)
            )
        }
        Spacer(Modifier.size(5.dp))
        Row {
            BeeqButton(
                onClick = ButtonAction.CoroutineAction(
                    onClick = {
                        errorSuspendFun()
                    },
                    onError = { t ->

                    }
                ),
                buttonSize = ButtonSize.Small,
                startIcon = Icons.Default.Adb,
                loading = false,
                enabled = true,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Primary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                startIcon = Icons.Default.Adb,
                buttonSize = ButtonSize.Small,
                enabled = false,
                style = ButtonStyle.NormalButton(BeeqButtonColors.Primary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                startIcon = Icons.Default.Home,
                buttonSize = ButtonSize.Small,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Primary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                startIcon = Icons.Default.Search,
                buttonSize = ButtonSize.Small,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Secondary)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                startIcon = Icons.Default.Check,
                buttonSize = ButtonSize.Small,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Success)
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                startIcon = Icons.Default.Dangerous,
                buttonSize = ButtonSize.Small,
                enabled = false,
                style = ButtonStyle.GhostButton(BeeqButtonColors.Danger)
            )
        }
    }
}

private suspend fun errorSuspendFun() {
    delay(3000)
    println()
    throw Throwable("test error")
}

private suspend fun suspendFun() {
    delay(3000)
    println()
}