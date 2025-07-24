package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.organisms.BeeqDropdown
import com.endava.beeq_components.organisms.BeeqDropdownItem
import com.endava.beeq_components.organisms.button.BeeqButton
import com.endava.beeq_components.organisms.button.BeeqButtonColors
import com.endava.beeq_components.organisms.button.ButtonSize
import com.endava.beeq_components.organisms.button.ButtonStyle

@Composable
fun DropdownScreen() {

    var selected by remember { mutableStateOf<BeeqDropdownItem?>(null) }
    val icon = rememberVectorPainter(image = Icons.Default.Lock)

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {

        SectionHeader("Dropdown")

        BeeqDropdown(
            items = listOf(
                BeeqDropdownItem(
                    text = "Option 1"
                ),
                BeeqDropdownItem(
                    text = "Option 2"
                ),
                BeeqDropdownItem(
                    text = "Option 3",
                    leadingIcon = icon
                ),
                BeeqDropdownItem(
                    trailingIcon = icon,
                    text = "Option 4"
                ),
                BeeqDropdownItem(
                    trailingIcon = icon,
                    leadingIcon = icon,
                    text = "Option 5"
                ),
            ),
            anchorContent = { expanded, onClick ->
                BeeqButton(
                    modifier = Modifier
                        .clickable { onClick() },
                    text = selected?.text ?: "Please Select",
                    buttonSize = ButtonSize.Small,
                    loading = false,
                    enabled = true,
                    endIcon = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    style = ButtonStyle.NormalButton(BeeqButtonColors.Secondary)
                )
            },
            selectedItem = selected,
            onItemSelected = { selected = it },
            keepOpenOnSelect = true
        )

    }
}