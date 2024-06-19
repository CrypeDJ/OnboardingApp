package com.crype.onboardingapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.crype.onboardingapp.R
import com.crype.onboardingapp.presentation.ui.theme.FourthScreenBackground

@Preview
@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FourthScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        val font = FontFamily(
            Font(R.font.sfpro_bold, weight = FontWeight.Bold),
            Font(R.font.sfpro_light, weight = FontWeight.Light)
        )
        Text(
            text = "You are a clever person!",
            fontSize = 25.sp,
            fontFamily = font,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}