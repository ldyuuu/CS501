package com.example.myapplication
import kotlin.random.Random
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
    var showText by remember { mutableIntStateOf(0) }
    var Texts:List<String> = listOf(
        "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful." ,
        "Start where you are. Use what you have. Do what you can." ,
        "The only place where success comes before work is in the dictionary." ,
        "Don’t wait for opportunity. Create it.",
        "Difficult roads often lead to beautiful destinations.",
        "What seems impossible today will one day become your warm-up.",
        "Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle." ,
        "Do something today that your future self will thank you for.",
        "The harder you work for something, the greater you’ll feel when you achieve it.",
        "Don’t limit your challenges. Challenge your limits."
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        Show(name = Texts.get(showText))

        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = {showText=Random.nextInt(0, 10) }) {
            Text(text = "Next")
        }

    }
}

@Composable
fun Show(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name",
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