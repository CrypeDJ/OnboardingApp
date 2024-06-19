package com.crype.onboardingapp.presentation.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crype.onboardingapp.R
import com.crype.onboardingapp.domain.model.OnboardingPageModel
import com.crype.onboardingapp.presentation.ui.theme.TranspanentWhite
import kotlin.io.path.Path


@Composable
fun PagerScreen (
    onBoardingPage: OnboardingPageModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(onBoardingPage.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {
        val fontFamilySF = FontFamily(
            Font(R.font.sfpro_bold, FontWeight.Bold),
            Font(R.font.sfpro_light, FontWeight.Light)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 40.dp)){
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = onBoardingPage.title,
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = onBoardingPage.description,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }

        }
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager Image",
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(40.dp).weight(1f))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 40.dp),

        ){
            Column (horizontalAlignment = Alignment.Start){
                Row(
                    Modifier
                        .wrapContentHeight()

                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(4) { iteration ->
                        val color = if (2 == iteration) Color.White else TranspanentWhite
                        val width = if (iteration == 2) 25.dp else 8.dp
                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .clip(CircleShape)
                                .background(color)
                                .height(8.dp)
                                .width(width)
                        )
                    }
                }
                TextButton(
                    onClick = { /*TODO*/ },

                ) {
                    Text (
                        text = "Skip",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        textAlign = TextAlign.Left
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            ButtonNext(progress = 1f, colorTextButton = onBoardingPage.backgroundColor) {}
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview (){
    Column (modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnboardingPageModel.ThirdPage)
    }
}

@Composable
fun ButtonNext(progress: Float, colorTextButton: Color, onClick: () -> Unit) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(60.dp)
    ) {
        Canvas(modifier = Modifier.size(100.dp)) {
            val sweepAngle = 360 * animatedProgress
            drawArc(
                color = TranspanentWhite,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.White,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Button(
            onClick = {  },
            shape = CircleShape,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            contentPadding = PaddingValues(
                start = 15.dp,
                top = 10.dp,
                end = 15.dp,
                bottom = 10.dp,
            )
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val path = androidx.compose.ui.graphics.Path().apply {
                        moveTo(size.width * 0.3f, size.height * 0.2f)
                        lineTo(size.width * 0.7f, size.height * 0.5f)
                        lineTo(size.width * 0.3f, size.height * 0.8f)
                    }
                    drawPath(path = path, color = colorTextButton, style = Stroke(width = 7f))
                }
            }
        }
    }
}

