package endava.beeq.compose.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import endava.beeq.compose.atoms.BeeqCheckboxIcon
import endava.beeq.compose.theme.colors.UIColors
import endava.beeq.compose.theme.TextStyles
import endava.beeq.compose.util.withEnable

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
            color = UIColors.black.withEnable(enabled)
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