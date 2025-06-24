package com.endava.beeq_components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CheckboxLabel(
    title: String,
    description: String? = null,
    hasError: Boolean = false
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (hasError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
        if (!description.isNullOrEmpty()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}