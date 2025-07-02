package com.endava.beeq_components.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.endava.beeq_components.atoms.Avatar
import com.endava.beeq_components.atoms.Badge
import com.endava.beeq_components.theme.StandardDimensions

@Composable
fun AvatarWithBadge(
    avatarModifier: Modifier = Modifier,
    imageUrl: String,
    initials: String = "",
    badgeContent: String? = null,
    avatarSize: StandardDimensions,
    badgeSize: Dp = avatarSize.value / 3
) {
    Box(modifier = Modifier) {
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
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarWithBadgePreview() {
    AvatarWithBadge(
        avatarModifier = Modifier,
        imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
        initials = "MS",
        avatarSize = StandardDimensions.XXL,
        badgeContent = "3"
    )
}
