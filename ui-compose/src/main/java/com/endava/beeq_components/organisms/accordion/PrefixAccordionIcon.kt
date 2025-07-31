package com.endava.beeq_components.organisms.accordion

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.atoms.Avatar
import com.endava.beeq_components.theme.StandardDimensions

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