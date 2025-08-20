package com.endava.ui_designview.models.avatar

import androidx.annotation.Keep

@Keep
sealed class AvatarTypeImage {

    @Keep
    data class AvatarImageUrl(
        val url: String?,
        val initials: String
    ): AvatarTypeImage()

    @Keep
    data class AvatarInitials(
        val initials: String
    ): AvatarTypeImage()
}