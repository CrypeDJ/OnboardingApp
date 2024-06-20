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
            .fillMaxWidth()
            .padding(top = 40.dp),
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
                    fontFamily = fontFamilySF,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = onBoardingPage.description,
                    fontSize = 18.sp,
                    fontFamily = fontFamilySF,
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
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview (){
    Column (modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnboardingPageModel.ThirdPage)
    }
}



