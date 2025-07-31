package endava.beeq.compose.organisms

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import endava.beeq.compose.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeeqDropdown(
    items: List<BeeqDropdownItem>,
    selectedItem: BeeqDropdownItem?,
    onItemSelected: (BeeqDropdownItem) -> Unit,
    anchorContent: @Composable (Boolean, () -> Unit) -> Unit,
    modifier: Modifier = Modifier,
    keepOpenOnSelect: Boolean = false,
    label: String = "Select...",
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    fun toggleExpand() {
        Log.d("AICI", "toggleExpand()")
        expanded = !expanded
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        anchorContent(expanded, ::toggleExpand).apply {
            modifier.menuAnchor(MenuAnchorType.PrimaryEditable, enabled)
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.text) },
                    trailingIcon = {
                        item.trailingIcon?.let {
                            Icon(
                                painter = it,
                                contentDescription = null,
                                tint = Colors.icon.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 8.dp)
                            )
                        }
                    },
                    leadingIcon = {
                        item.leadingIcon?.let {
                            Icon(
                                painter = it,
                                contentDescription = null,
                                tint = Colors.icon.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 8.dp)
                            )
                        }
                    },
                    onClick = {
                        onItemSelected(item)
                        if (!keepOpenOnSelect) expanded = false
                    }
                )
            }
        }
    }
}

data class BeeqDropdownItem(
    val text: String,
    val onClick: () -> Unit = {},
    val leadingIcon: Painter? = null,
    val trailingIcon: Painter? = null
)
