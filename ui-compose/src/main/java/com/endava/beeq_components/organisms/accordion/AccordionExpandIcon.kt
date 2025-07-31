package endava.beeq.compose.organisms.accordion

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import endava.beeq.compose.theme.colors.UIColors
import endava.beeq.compose.util.withEnable

enum class AccordionExpandIcon {
    Chevron, Plus;

    @Composable
    fun ExpandIcon(expanded: Boolean, enabled: Boolean) = when (this) {
        Plus -> {
            Icon(
                imageVector = if (expanded) Icons.Default.Remove else Icons.Default.Add,
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurface.withEnable(enabled)
            )
        }

        Chevron -> {
            Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier
                    .rotate(if (expanded) 180f else 0f)
                    .size(20.dp),
                tint = UIColors.black.withEnable(enabled)
            )
        }
    }
}