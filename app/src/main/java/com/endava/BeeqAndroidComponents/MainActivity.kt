package com.endava.BeeqAndroidComponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.BeeqAndroidComponents.ui.theme.SampleappTheme
import com.endava.beeq_components.organisms.AvatarWithBadge
import com.endava.beeq_components.organisms.button.BeeqButton
import com.endava.beeq_components.organisms.button.BeeqButtonStyle
import com.endava.beeq_components.organisms.button.ButtonSize
import com.endava.beeq_components.theme.BrandColors
import com.endava.beeq_components.theme.StandardDimensions
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BeeqCheckbox(
            state = BeeqCheckboxState.CHECKED,
            onCheckedChange = {},
            enabled = false,
            indeterminate = true,
            title = "Subscribe to newsletter sdjnasdlkajns dajsnd lkajsnd lakjsndlasjbdn lakjsb dlaksjb dlaksjd alsjbd laksj bdlasjb dlaskbd",
        )
    }
}

private suspend fun suspendFun() {
    delay(3000)
    println()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleappTheme {
        Greeting("Android")
    }
}