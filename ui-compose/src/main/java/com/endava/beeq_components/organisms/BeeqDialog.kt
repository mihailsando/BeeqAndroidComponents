package endava.beeq.compose.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import endava.beeq.compose.organisms.button.BeeqButtonColors
import endava.beeq.compose.theme.Colors

@Composable
fun BeeqDialog(
    title: String? = null,
    icon: Painter? = null,
    content: @Composable ColumnScope.() -> Unit,
    footer: (@Composable RowScope.() -> Unit)? = null,
    highlightFooter: Boolean = false,
    dismissOnClickOutside: Boolean = true,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            if (dismissOnClickOutside) {
                onDismissRequest()
            }
        }
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                // Title + Icon Row
                if (!title.isNullOrEmpty() || icon != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 24.dp, top = 20.dp, end = 24.dp)
                    ) {
                        icon?.let {
                            Icon(
                                painter = it,
                                contentDescription = null,
                                tint = Colors.icon.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 8.dp)
                            )
                        }


                        if (!title.isNullOrEmpty()) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Content
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxWidth()
                ) {
                    content()
                }

                // Footer or fallback bottom padding
                if (footer != null) {
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (highlightFooter)
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                                else MaterialTheme.colorScheme.surface
                            )
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        content = footer
                    )
                } else {
                    Spacer(modifier = Modifier.height(16.dp)) // bottom padding when no footer
                }
            }
        }
    }
}
