package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.sp
import java.util.Calendar
import androidx.compose.material3.OutlinedTextField
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }
    var text1 by remember {mutableStateOf("") }
    var showText by remember {mutableStateOf(0) }
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }

    // Determine the appropriate greeting
    val greeting = when (currentHour) {
        in 5..11 -> "Good morning! Hope you have a fantastic day ahead."
        in 12..16 -> "Good afternoon! How's your day going so far?"
        in 17..21 -> "Good evening! How was your day?"
        else -> "It’s quite late, isn’t it? Hope you’re having a calm night."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text("Enter name") }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            text1=text
            showText=1}
        ) {
            Text(text = "Enter")
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (showText==1) Show(name = text1,greetings=greeting)

    }
}

@Composable
fun Show(name: String, greetings: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!\n"+"$greetings",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color.DarkGray,
            shadow = Shadow(
                color = Color.Gray,
                offset = androidx.compose.ui.geometry.Offset(4f, 4f),
                blurRadius = 8f
            ),
        ),
        modifier = Modifier,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis

    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}