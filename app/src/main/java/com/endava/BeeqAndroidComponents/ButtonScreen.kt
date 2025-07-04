package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.organisms.button.BeeqButton
import com.endava.beeq_components.organisms.button.BeeqButtonStyle
import com.endava.beeq_components.organisms.button.ButtonSize
import kotlinx.coroutines.delay

@Composable
fun ButtonScreen() {
    Column {
        SectionHeader("Button")

        Row {
            BeeqButton(
                coroutineAction = {},
                text = "Primary",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.Person,
                style = BeeqButtonStyle.Primary
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                onClick = {},
                text = "Primary disable ",
                buttonSize = ButtonSize.Small,
                enabled = false,
                startIcon = Icons.Default.Person,
                style = BeeqButtonStyle.Primary
            )
        }

        Row {
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Danger",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Search,
                style = BeeqButtonStyle.Danger
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Danger disable",
                buttonSize = ButtonSize.Small,
                enabled = false,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.Search,
                style = BeeqButtonStyle.Danger
            )
        }

        Row {
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Secondary",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.MailOutline,
                style = BeeqButtonStyle.Secondary
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Secondary disable",
                buttonSize = ButtonSize.Small,
                enabled = false,
                startIcon = Icons.Default.DateRange,
                endIcon = Icons.Default.MailOutline,
                style = BeeqButtonStyle.Secondary
            )
        }

        Row {
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Ghost",
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                endIcon = Icons.Default.Warning,
                style = BeeqButtonStyle.Ghost
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Ghost disable",
                buttonSize = ButtonSize.Small,
                enabled = false,
                endIcon = Icons.Default.Warning,
                style = BeeqButtonStyle.Ghost
            )
        }

        Row {
            BeeqButton(
                onClick = {},
                text = "Text",
                coroutineAction = {suspendFun()},
                buttonSize = ButtonSize.Small,
                loading = false,
                enabled = true,
                style = BeeqButtonStyle.Text
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "Text",
                buttonSize = ButtonSize.Small,
                enabled = false,
                style = BeeqButtonStyle.Text
            )
        }

        Row {
            BeeqButton(
                onClick = {},
                text = "",
                coroutineAction = {suspendFun()},
                buttonSize = ButtonSize.Small,
                startIcon = Icons.Default.Adb,
                loading = false,
                enabled = true,
                style = BeeqButtonStyle.Text
            )
            Spacer(Modifier.size(5.dp))
            BeeqButton(
                coroutineAction = {suspendFun()},
                text = "",
                startIcon = Icons.Default.Adb,
                buttonSize = ButtonSize.Small,
                enabled = false,
                style = BeeqButtonStyle.Text
            )
        }
    }
}

private suspend fun suspendFun() {
    delay(3000)
    println()
}