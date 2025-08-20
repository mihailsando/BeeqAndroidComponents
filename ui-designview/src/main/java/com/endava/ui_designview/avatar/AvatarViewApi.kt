package com.endava.ui_designview.avatar

import androidx.annotation.Keep
import com.endava.ui_designview.models.avatar.AvatarTypeImage

@Keep
interface AvatarViewApi {
    fun setInitials(initials: String)
    fun setImageUrl(url: String?)

    fun setType (
        avatarType: AvatarTypeImage
    )

    fun setSizeAndShape(
        sizeAttr: Int,
        shapeAttr: Int,
        height: Int
    )

    fun showDot(showDot: Boolean)

    fun setDotText(dotContent: String)
    fun setColorDot(dotColor : Int)
}