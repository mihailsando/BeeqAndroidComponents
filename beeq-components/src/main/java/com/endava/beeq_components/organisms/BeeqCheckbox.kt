package com.endava.beeq_components.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.molecules.CheckboxLabel
import com.endava.beeq_components.atoms.BeeqCheckboxIcon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column

@Composable
fun BeeqCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    title: String,
    description: String? = null,
    indeterminate: Boolean = false,
    enabled: Boolean = true,
    hasError: Boolean = false,
    modifier: Modifier = Modifier
) {
    val toggleState = if (enabled && onCheckedChange != null) {
        Modifier.clickable { onCheckedChange(!checked) }
    } else Modifier

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(toggleState)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BeeqCheckboxIcon(
            checked = checked,
            indeterminate = indeterminate,
            enabled = enabled
        )
        Spacer(modifier = Modifier.width(12.dp))
        CheckboxLabel(
            title = title,
            description = description,
            hasError = hasError
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BeeqCheckboxPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        BeeqCheckbox(
            checked = true,
            onCheckedChange = {},
            title = "I agree to the terms"
        )
        Spacer(modifier = Modifier.height(12.dp))
        BeeqCheckbox(
            checked = false,
            onCheckedChange = {},
            title = "Subscribe to newsletter",
            description = "Receive monthly product updates."
        )
        Spacer(modifier = Modifier.height(12.dp))
        BeeqCheckbox(
            checked = false,
            onCheckedChange = {},
            title = "Accept license",
            hasError = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        BeeqCheckbox(
            checked = false,
            onCheckedChange = null,
            title = "Disabled option",
            enabled = false
        )
    }
}