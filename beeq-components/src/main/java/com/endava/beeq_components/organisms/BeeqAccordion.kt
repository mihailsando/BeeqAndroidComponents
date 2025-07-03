package com.endava.beeq_components.organisms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.atoms.Avatar
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.StandardDimensions
import com.endava.beeq_components.theme.TextStyles
import com.endava.beeq_components.util.withEnable

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
                tint = BrandColors.black.withEnable(enabled)
            )
        }
    }
}

sealed class PrefixAccordionIcon {

    abstract val content: (@Composable () -> Unit)?

    class AvatarPrefix(
        val initials: String,
        val imageUrl: String?
    ) : PrefixAccordionIcon() {
        override val content: @Composable (() -> Unit)?
            get() = {
                Avatar(
                    initials = initials,
                    imageUrl = imageUrl,
                    size = StandardDimensions.L
                )
            }
    }

    class ImagePrefix(
        val imageVector: ImageVector
    ) : PrefixAccordionIcon() {
        override val content: @Composable (() -> Unit)?
            get() = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(20.dp),
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
    }

    class BlankPrefix() : PrefixAccordionIcon() {
        override val content: @Composable (() -> Unit)? = null
    }
}

@Composable
fun BeeqAccordion(
    title: String,
    content: @Composable () -> Unit,
    onSettingsClicked: (() -> Unit)? = null,
    prefixContent: PrefixAccordionIcon,
    accordionExpandIcon: AccordionExpandIcon = AccordionExpandIcon.Plus,
    background: Color? = BrandColors.accordionBackground,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        // Header row
        Row(
            modifier = Modifier
                .clickable(
                    role = Role.DropdownList,
                    enabled = enabled,
                    onClick = { expanded = !expanded }
                )
                .fillMaxWidth()
                .background(background ?: MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            prefixContent.content?.let { content ->
                Box(modifier = Modifier.withEnable(enabled)) { content() }
            }

            Text(
                text = title,
                color = BrandColors.black.withEnable(enabled),
                style = TextStyles.AccordionText,
                modifier = Modifier
                    .padding(start = StandardDimensions.S.value)
                    .weight(1f)
            )

            onSettingsClicked?.let { openSettings ->
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable(enabled = enabled) {
                            openSettings()
                        }
                        .withEnable(enabled)
                        .size(20.dp),
                    tint = BrandColors.black
                )
            }

            accordionExpandIcon.ExpandIcon(
                expanded = expanded,
                enabled = enabled
            )
        }

        // Animated content
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .withEnable(enabled)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BeeqAccordionPreview() {
    BeeqAccordion(
        title = "Header",
        onSettingsClicked = {},
        prefixContent = PrefixAccordionIcon.ImagePrefix(imageVector = Icons.Default.Expand),
        enabled = true,
        content = {
            Text("Accordion content goes here.")
        }
    )
}