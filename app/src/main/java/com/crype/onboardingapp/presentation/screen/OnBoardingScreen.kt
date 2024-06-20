package com.crype.onboardingapp.presentation.screen

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.crype.onboardingapp.R
import com.crype.onboardingapp.domain.model.OnboardingPageModel
import com.crype.onboardingapp.domain.navigation.Screen
import com.crype.onboardingapp.presentation.ui.theme.TranspanentWhite
import kotlinx.coroutines.launch
import kotlin.io.path.ExperimentalPathApi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen (
    navController: NavHostController
    ) {
    val animationScope = rememberCoroutineScope()
    val animationTime: Int = 500
    val fontFamilySF = FontFamily(
        Font(R.font.sfpro_bold, FontWeight.Bold),
        Font(R.font.sfpro_light, FontWeight.Light),
        Font(R.font.abel, FontWeight.ExtraLight)
    )
    val pages = listOf(
        OnboardingPageModel.FirstPage,
        OnboardingPageModel.SecondPage,
        OnboardingPageModel.ThirdPage,
        OnboardingPageModel.FourthPage
        )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    Column (modifier = Modifier
        .fillMaxSize()
        .background(pages[pagerState.currentPage].backgroundColor)
        .navigationBarsPadding()
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 40.dp),

            ) {
            Column (horizontalAlignment = Alignment.Start){
                Row(
                    Modifier
                        .wrapContentHeight()

                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    repeat(4) {
                        iteration ->
                        val (color: Color, width:Dp) = if (pagerState.currentPage == iteration) Color.White to 25.dp
                        else TranspanentWhite to 8.dp
                        val animatedWidth by animateDpAsState(
                            targetValue = width,
                            label = "",
                            animationSpec = tween(durationMillis = animationTime)
                        )
                        val animatedColor by animateColorAsState(
                            targetValue = color,
                            label = "",
                            animationSpec = tween(durationMillis = animationTime)
                        )
                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .clip(CircleShape)
                                .background(animatedColor)
                                .height(8.dp)
                                .width(animatedWidth)
                        )
                    }
                }
                Text (
                    text = "Skip",
                    fontSize = 16.sp,
                    fontFamily = fontFamilySF,
                    fontWeight = FontWeight.ExtraLight,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            ButtonNext(
                progress = pagerState.currentPage.toFloat(),
                colorTextButton = pages[pagerState.currentPage].backgroundColor,
                animationTime = animationTime,
                onNextClick = {
                    if (pagerState.currentPage >= pages.size -1 ) {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    } else {
                        animationScope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(durationMillis = animationTime)
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ButtonNext(progress: Float, colorTextButton: Color, animationTime: Int,onNextClick: () -> Unit) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = animationTime),
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(60.dp)
    ) {
        Canvas(modifier = Modifier.size(100.dp)) {
            val sweepAngle = 90 * animatedProgress + 90
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
            onClick = onNextClick,
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
                    val path = Path().apply {
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



