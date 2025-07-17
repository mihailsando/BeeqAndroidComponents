package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.organisms.BeeqCard
import com.endava.beeq_components.organisms.CardEndIcon
import com.endava.beeq_components.organisms.CardType
import com.endava.beeq_components.organisms.MiniCardAvatar
import com.endava.beeq_components.organisms.button.BeeqButton
import com.endava.beeq_components.organisms.button.BeeqButtonColors
import com.endava.beeq_components.organisms.button.ButtonAction
import com.endava.beeq_components.organisms.button.ButtonSize
import com.endava.beeq_components.organisms.button.ButtonStyle
import kotlinx.coroutines.delay

@Composable
fun CardScreen() {
    val miniCardType = CardType.MiniCard(
        avatar = MiniCardAvatar(
            icon = Icons.Default.Home
        ),
        endIcon = CardEndIcon(
            icon = Icons.Default.OpenInNew,
            onClick = { println("MiniCard EndIcon clicked") }
        )
    )

    val miniCardTypeReverse = CardType.MiniCard(
        avatar = MiniCardAvatar(
            icon = Icons.Default.Image,
            reverseColors = false
        ),

        )

    val miniCardWithoutEndIcon = CardType.MiniCard(
        avatar = MiniCardAvatar(
            icon = Icons.Default.Mail,
            reverseColors = false
        ),
        endIcon = CardEndIcon(
            icon = Icons.Default.OpenInNew,
            onClick = { println("MiniCard EndIcon clicked") }
        )
    )

    val cardType = CardType.Card(
        icon = Icons.Outlined.Star
    )

    Column {
        SectionHeader("Cards")

        BeeqCard(
            title = "Mini Card Title",
            type = miniCardWithoutEndIcon,
            description = {
                Text(
                    text = "This is a short description. ",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            },
            modifier = Modifier.padding(16.dp)
        )

        BeeqCard(
            title = "Mini Card Title",
            type = miniCardTypeReverse,
            description = {
                Text(
                    text = "This is a short description. ",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            },
            modifier = Modifier.padding(16.dp)
        )

        BeeqCard(
            title = "Mini Card Title",
            type = miniCardType,
            description = {
                Text(
                    text = "This is a short description. \n dadasd dasd \n dadsasdasdasd",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            },
            modifier = Modifier.padding(16.dp)
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

        BeeqCard(
            title = null,
            type = CardType.Card(),
            description = {
                Column {
                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BeeqButton(
                        startIcon = Icons.Default.Check,
                        onClick = ButtonAction.CoroutineAction(
                            onClick = {
                                delay(3000)
                                println()
                            }
                        ),
                        text = "Button Example",
                        buttonSize = ButtonSize.Small,
                        style = ButtonStyle.NormalButton(BeeqButtonColors.Primary)
                    )
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    CardScreen()
}