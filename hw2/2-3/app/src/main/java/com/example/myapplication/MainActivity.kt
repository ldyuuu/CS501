package com.example.myapplication

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
import kotlin.random.Random

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
    var index by remember {mutableIntStateOf(Random.nextInt(0,12)) }
    var showText by remember {mutableStateOf(0) }
    var randomIndex by remember { mutableIntStateOf(Random.nextInt(0,6)) }
    val funFacts: List<String> = listOf(
        "Seahorses are the only animals in which the males give birth. Female seahorses transfer their eggs into the male's pouch, and he carries them until they hatch.",
        "Honey is the only food that doesn't spoil. Archaeologists have found pots of honey in ancient Egyptian tombs that are over 3,000 years old and still edible.",
        "The human tongue print is as unique as a fingerprint. Every person has a distinct tongue print.",
        "Antarctica is the largest desert in the world. Despite being covered in ice and snow, its low rainfall classifies it as a desert.",
        "An octopus has three hearts: two pump blood to the gills, while the third pumps it to the rest of the body.",
        "Cherries aren't always red. Depending on the variety, they can also be yellow, pink, or even black.",
        "A zebra's stripes are unique. No two zebras have the exact same stripe pattern, much like human fingerprints.",
        "The whale shark is the largest fish in the world, yet it feeds on plankton and small fish, not large prey.",
        "Sunflowers can purify soil. They absorb harmful substances like lead, arsenic, and uranium through their roots, making them useful for cleaning contaminated land.",
        "Birds are the direct descendants of dinosaurs. Their lightweight skeletons evolved from small, meat-eating dinosaurs.",
        "Mars has a mountain three times taller than Mount Everest. Olympus Mons is the tallest volcano in the solar system, standing at 22 kilometers high.",
        "Some turtles can breathe through their butt. During hibernation, certain turtles can absorb oxygen through their cloaca, allowing them to survive underwater for long periods."
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        Show(text = funFacts.get(index),Index= randomIndex)


        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            randomIndex=Random.nextInt(0,6)
            index=(index+1)%12
            showText=1}
        ) {
            Text(text = "Next Fact")
        }
    }
}

@Composable
fun Show(text: String, Index: Int, modifier: Modifier = Modifier) {
    val colorPairs = listOf(
        Pair(Color(0xFF2196F3), Color(0xFF1976D2)),
        Pair(Color(0xFFFFC107), Color(0xFFFFA000)),
        Pair(Color(0xFF4CAF50), Color(0xFF388E3C)),
        Pair(Color(0xFFFF5722), Color(0xFFE64A19)),
        Pair(Color(0xFF9C27B0), Color(0xFF7B1FA2)),
        Pair(Color(0xFF00BCD4), Color(0xFF0097A7))
    )

    val (textColor, shadowColor) = colorPairs[Index]

    Text(
        text = "$text\n",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = textColor,
            shadow = Shadow(
                color = shadowColor.copy(alpha = 0.5f),
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