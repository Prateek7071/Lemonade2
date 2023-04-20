package com.example.lemonade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade2.ui.theme.Lemonade2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currrentStep by remember { mutableStateOf(1) }
    var squeezecount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currrentStep) {
            1 -> {
                LemonadeImgText(
                    textLabel = R.string.state1,
                    drawableR = R.drawable.lemon_tree,
                    contentDesc = R.string.LemonTree,
                    onImageClick = {
                        currrentStep = 2
                        squeezecount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonadeImgText(
                    textLabel = R.string.state2,
                    drawableR = R.drawable.lemon_squeeze,
                    contentDesc = R.string.Lemon,
                    onImageClick = {
                        squeezecount--
                        if (squeezecount == 0)
                            currrentStep = 3
                    },
                )
            }
            3 -> {
                LemonadeImgText(
                    textLabel = R.string.state3,
                    drawableR = R.drawable.lemon_drink,
                    contentDesc = R.string.Glass_of_Lemonade,
                    onImageClick = {
                        currrentStep = 4
                    }
                )
            }
            4 -> {
                LemonadeImgText(
                    textLabel = R.string.state4,
                    drawableR = R.drawable.lemon_restart,
                    contentDesc = R.string.Empty_glass,
                    onImageClick = {
                        currrentStep = 1
                    })
            }
        }
    }


}

@Composable
fun LemonadeImgText(
    textLabel: Int,
    drawableR: Int,
    contentDesc: Int,
    onImageClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(textLabel), fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter= painterResource(drawableR),
            contentDescription = stringResource(contentDesc),
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)

        )

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lemonade2Theme {
        LemonadeApp()
    }
}