package com.endava.beeq_components.organisms.accordion

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
import androidx.compose.material.icons.filled.Expand
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.StandardDimensions
import com.endava.beeq_components.theme.TextStyles
import com.endava.beeq_components.util.withEnable

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