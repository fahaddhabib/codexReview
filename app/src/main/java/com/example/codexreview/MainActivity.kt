package com.example.codexreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codexreview.ui.theme.CodexreviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodexreviewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReviewResultScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ReviewResultScreen(modifier: Modifier = Modifier) {
    var reviewMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = reviewMessage)

        Button(onClick = { reviewMessage = "code review successful" }) {
            Text(text = "Show Result")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewResultScreenPreview() {
    CodexreviewTheme {
        ReviewResultScreen()
    }
}
