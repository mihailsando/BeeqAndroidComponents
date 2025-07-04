package com.endava.BeeqAndroidComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.endava.beeq_components.organisms.accordion.AccordionExpandIcon
import com.endava.beeq_components.organisms.accordion.BeeqAccordion
import com.endava.beeq_components.organisms.accordion.PrefixAccordionIcon

@Composable
fun AccordionScreen() {
    SectionHeader("Accordion")

    BeeqAccordion(
        title = "Header Chevron",
        onSettingsClicked = {},
        accordionExpandIcon = AccordionExpandIcon.Chevron,
        prefixContent = PrefixAccordionIcon.AvatarPrefix(
            initials = "SS",
            imageUrl = "https://i.pravatar.cc/150?img=7"
        ),
        enabled = true,
        content = {
            Text("Accordion content goes here.")
        }
    )

    BeeqAccordion(
        title = "Header Plus",
        onSettingsClicked = {},
        accordionExpandIcon = AccordionExpandIcon.Plus,
        prefixContent = PrefixAccordionIcon.ImagePrefix(
            imageVector = Icons.Default.DateRange
        ),
        enabled = true,
        content = {
            Text("Accordion content goes here.")
        }
    )
    BeeqAccordion(
        title = "Header no settings",
        accordionExpandIcon = AccordionExpandIcon.Plus,
        prefixContent = PrefixAccordionIcon.BlankPrefix(),
        enabled = false,
        content = {
            Text("Accordion content goes here.")
        }
    )
    BeeqAccordion(
        title = "Header disable",
        onSettingsClicked = {},
        accordionExpandIcon = AccordionExpandIcon.Plus,
        prefixContent = PrefixAccordionIcon.ImagePrefix(
            imageVector = Icons.Default.Lock
        ),
        initiallyExpanded = true,
        enabled = false,
        content = {
            Text("Accordion content goes here.")
        }
    )
}