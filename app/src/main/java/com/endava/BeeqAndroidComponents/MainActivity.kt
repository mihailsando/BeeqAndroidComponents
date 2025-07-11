package com.endava.BeeqAndroidComponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.endava.BeeqAndroidComponents.ui.AvatarScreen
import com.endava.BeeqAndroidComponents.ui.theme.SampleappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleappTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
                ) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val allScreens = listOf<@Composable () -> Unit>(
    { CardScreen() },
    { ButtonScreen() },
    { AccordionScreen() },
    { AlertInfoScreen() },
    { AvatarScreen() },

    { Spacer(modifier = Modifier.padding(50.dp)) }
)

@Composable
fun Greeting( modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        allScreens.forEach { screen ->
            screen()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleappTheme {
        Greeting()
    }
}