package com.endava.BeeqAndroidComponents.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.endava.BeeqAndroidComponents.SectionHeader
import com.endava.beeq_components.organisms.AvatarWithBadge
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.StandardDimensions

@Composable
fun AvatarScreen(){
    SectionHeader("Avatar")

    Row {
        AvatarWithBadge(
            initials = "SS",
            imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            badgeContent = "online",
            avatarSize = StandardDimensions.XXL2,
            badgeColor = BrandColors.success
        )
        AvatarWithBadge(
            initials = "SS",
            imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            badgeContent = "online",
            avatarSize = StandardDimensions.XXL,
            badgeColor = BrandColors.success)

        AvatarWithBadge(
            initials = "SS",
            imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            badgeContent = "33",
            avatarSize = StandardDimensions.XXL,
            badgeColor = BrandColors.danger
        )
        AvatarWithBadge(
            initials = "SS",
            imageUrl = "https://randomuser.en/32.jpg",
            badgeContent = "    ",
            avatarSize = StandardDimensions.XXL,
            badgeColor = BrandColors.danger
        )
        AvatarWithBadge(
            initials = "SS",
            imageUrl = "https://randomn/32.jpg",
            avatarSize = StandardDimensions.L,
        )
        Spacer(modifier = Modifier.padding(12.dp))
        AvatarWithBadge(
            initials = "SS",
            imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            avatarSize = StandardDimensions.M,
        )
    }
}