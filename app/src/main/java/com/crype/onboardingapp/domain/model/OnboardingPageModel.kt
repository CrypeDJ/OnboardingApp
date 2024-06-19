package com.crype.onboardingapp.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.crype.onboardingapp.R
import com.crype.onboardingapp.presentation.ui.theme.FirstScreenBackground
import com.crype.onboardingapp.presentation.ui.theme.FourthScreenBackground
import com.crype.onboardingapp.presentation.ui.theme.SecondScreenBackground
import com.crype.onboardingapp.presentation.ui.theme.ThirdScreenBackground

sealed class OnboardingPageModel(
    val title: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val backgroundColor: Color
) {
    data object FirstPage: OnboardingPageModel(
        title = "Your first car without a driver's license",
        description = "Goes to meet people who just got their license",
        image = R.drawable.img_car1,
        backgroundColor = FirstScreenBackground
    )
    data object SecondPage: OnboardingPageModel(
        title = "Always there: more than 1000 cars in Tbilisi",
        description = "Our company is a leader by the number of cars in the fleet",
        image = R.drawable.img_car2,
        backgroundColor = SecondScreenBackground
    )
    data object ThirdPage: OnboardingPageModel(
        title = "Do not pay for parking, maintenance and gasoline",
        description = "We will pay for you, all expenses related to the car",
        image = R.drawable.img_car3,
        backgroundColor = ThirdScreenBackground
    )
    data object FourthPage: OnboardingPageModel(
        title = "29 car models: from Skoda Octavia to Porsche 911",
        description = "Choose between regular car models or exclusive ones",
        image = R.drawable.img_car4,
        backgroundColor = FourthScreenBackground

    )
}
