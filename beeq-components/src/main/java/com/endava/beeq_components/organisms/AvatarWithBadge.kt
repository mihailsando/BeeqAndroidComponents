package com.endava.beeq_components.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.atoms.Avatar
import com.endava.beeq_components.atoms.Badge
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.StandardDimensions

@Composable
fun AvatarWithBadge(
    avatarModifier: Modifier = Modifier,
    imageUrl: String,
    initials: String = "",
    badgeContent: String? = null,
    avatarSize: StandardDimensions = StandardDimensions.L,
    badgeSize: Dp = avatarSize.value / 4,
    badgeColor: Color = BrandColors.danger
) {
    Box(modifier = Modifier) {
        Row {
            Avatar(
                modifier = avatarModifier
                    .size(avatarSize.value),
                imageUrl = imageUrl,
                initials = initials,
                size = avatarSize,
            )
            badgeContent?.let {
                Badge(
                    content = it,
                    size = badgeSize,
                    backgroundColor = badgeColor,
                    modifier = Modifier
                        .offset(x = (-avatarSize.value / 3 ), y = 0.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AvatarWithBadgePreview() {
    AvatarWithBadge(
        initials = "SS",
        imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
        badgeContent = "online",
        avatarSize = StandardDimensions.XXL2,
        badgeColor = BrandColors.success
    )
}
