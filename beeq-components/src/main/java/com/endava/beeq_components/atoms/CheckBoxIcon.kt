package com.endava.beeq_components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.MutableState
import com.endava.beeq_components.organisms.BeeqCheckboxState
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.util.withEnable

@Composable
fun BeeqCheckboxIcon(
    state: BeeqCheckboxState,
    enabled: Boolean = true
) {
    val icon = when(state) {
        BeeqCheckboxState.MINUS -> Icons.Default.Remove
        BeeqCheckboxState.PLUS -> Icons.Default.Add
        BeeqCheckboxState.CHECKED -> Icons.Default.Check
        BeeqCheckboxState.UNCHECKED -> null
    }

    Box(
        modifier = Modifier
            .size(20.dp)
            .background(
                color = if (state != BeeqCheckboxState.UNCHECKED ) BrandColors.brand.withEnable(enabled)
                else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color =MaterialTheme.colorScheme.onSurface.withEnable(enabled),
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}