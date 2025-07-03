package com.endava.beeq_components.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.beeq_components.atoms.BeeqCheckboxIcon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.semantics.Role
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.TextStyles
import com.endava.beeq_components.util.withEnable

enum class BeeqCheckboxState {
    CHECKED, UNCHECKED, MINUS, PLUS;

    operator fun not(): BeeqCheckboxState = when (this) {
        CHECKED -> UNCHECKED
        UNCHECKED -> CHECKED
        MINUS -> PLUS
        PLUS -> MINUS
    }

}

@Composable
fun BeeqCheckbox(
    state: BeeqCheckboxState,
    onCheckedChange: ((BeeqCheckboxState) -> Unit) = {},
    title: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val state = remember { mutableStateOf(state) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Checkbox,
                enabled = enabled,
                onClick = {
                    state.value = !state.value
                    onCheckedChange(state.value)
                }
            )
            .padding(vertical = 8.dp),
    ) {
        BeeqCheckboxIcon(
            state = state.value,
            enabled = enabled,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            style = TextStyles.CheckBoxText,
            color = BrandColors.black.withEnable(enabled)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BeeqCheckboxPreview() {
   BeeqCheckbox(
            state = BeeqCheckboxState.CHECKED,
            onCheckedChange = {},
            enabled = false,
            title = "Subscribe to newsletter sdjnasdlkajns dajsnd lkajsnd lakjsndlasjbdn lakjsb dlaksjb dlaksjd alsjbd laksj bdlasjb dlaskbd",
        )
}