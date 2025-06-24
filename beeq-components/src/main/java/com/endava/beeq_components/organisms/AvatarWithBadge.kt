package com.endava.beeq_components.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.atoms.Avatar
import com.endava.beeq_components.atoms.Badge

@Composable
fun AvatarWithBadge(
    imageUrl: String,
    badgeContent: String? = null,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 48.dp,
    badgeSize: Dp = 16.dp
) {
    Box(modifier = modifier.size(avatarSize)) {
        Avatar(
            imageUrl = imageUrl,
            modifier = Modifier.matchParentSize()
        )
        badgeContent?.let {
            Badge(
                content = it,
                modifier = Modifier
                    .size(badgeSize)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarWithBadgePreview() {
    AvatarWithBadge(
        imageUrl = "https://randomuser.me/api/portraits/men/32.jpg",
        badgeContent = "3"
    )
}
