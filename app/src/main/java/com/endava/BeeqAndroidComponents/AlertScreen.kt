package com.endava.BeeqAndroidComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.organisms.AlertBeeq
import com.endava.beeq_components.organisms.AlertType
import com.endava.beeq_components.organisms.DescriptionWithLink

@Composable
fun AlertInfoScreen() {

    Column(modifier = Modifier.padding(16.dp)) {
        SectionHeader("Alert Info")

        AlertBeeq(
            alertType = AlertType.Info,
            title = "test message",
            onClose = {

            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Success,
            title = "test message",
            hideIcon = true,
            onClose = {}
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Warning,
            title = "test message",
            hideCloseIcon = true,
            onClose = {},
            description = "description"
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Error,
            onClose = {},
            title = "test message",
            description = "description, description, description",
            descriptionComposable = {
                DescriptionWithLink(
                    description = "For more details, check the ",
                    linkText = "documentation",
                    onLinkClick = { }
                )
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AlertBeeq(
            alertType = AlertType.Message,
            title = "test message",
            onClose = {},
            hideCloseIcon = true,
            description = "description, description, description",
            descriptionComposable = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A73E8), // Blue
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "button 1")
                    }

                    TextButton(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color(0xFF1A73E8) // Blue
                        )
                    ) {
                        Text(text = "button 2")
                    }
                }
            }
        )
    }
}