package endava.beeq.compose.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import endava.beeq.compose.theme.Colors

data class MiniCardAvatar(
    val icon: ImageVector,
    val reverseColors: Boolean = true
) {
    @get:Composable
    val bgColor: Color
        get() = if (reverseColors)
            Colors.icon.brand
        else Colors.bg.secondary

    @get:Composable
    val iconColor: Color
        get() = if (reverseColors)
            Colors.bg.secondary
        else Colors.icon.brand
}

data class CardEndIcon(
    val icon: ImageVector,
    val onClick: (() -> Unit)?
)

sealed interface CardType {

    data class MiniCard(
        val avatar: MiniCardAvatar,
        val endIcon: CardEndIcon? = null
    ) : CardType

    data class Card(val icon: ImageVector? = null) : CardType
}

@Composable
fun BeeqCard(
    title: String?,
    type: CardType,
    cardCornerRadius: Dp = 16.dp,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        shape = RoundedCornerShape(cardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = Colors.bg.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        when (type) {
            is CardType.MiniCard -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .width(52.dp)
                            .fillMaxHeight()
                            .clip(
                                RoundedCornerShape(
                                    topStart = cardCornerRadius,
                                    bottomStart = cardCornerRadius
                                )
                            )
                            .background(type.avatar.bgColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = type.avatar.icon,
                            contentDescription = "MiniCard avatar",
                            tint = type.avatar.iconColor
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        title?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black
                            )
                        }
                        description()
                        Spacer(modifier = Modifier.height(8.dp))

                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    type.endIcon?.icon?.let { endImage ->
                        val onClick = type.endIcon.onClick
                        Icon(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable(enabled = onClick != null) {
                                    onClick?.invoke()
                                },
                            imageVector = endImage,
                            contentDescription = "External link",
                            tint = Colors.icon.brand
                        )
                    }

                }
            }

            is CardType.Card -> {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    type.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            tint = Colors.icon.brand,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Colors.text.primary
                        )
                    }

                    description()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MiniCardPreview() {
    val cardType = CardType.MiniCard(
        avatar = MiniCardAvatar(
            icon = Icons.Default.Home,
            reverseColors = true
        ),
        endIcon = CardEndIcon(
            icon = Icons.Default.OpenInNew,
            onClick = { println("MiniCard EndIcon clicked") }
        )
    )

    BeeqCard(
        title = "Mini Card Title",
        type = cardType,
        description = {
            Text(
                text = "This is a short description. \n dadasd dasd \n dadsasdasdasd",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        },
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FullCardPreview() {
    val cardType = CardType.Card(
        icon = Icons.Outlined.Star
    )

    BeeqCard(
        title = "Full Card Title",
        type = cardType,
        description = {
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        },
        modifier = Modifier.padding(16.dp)
    )
}