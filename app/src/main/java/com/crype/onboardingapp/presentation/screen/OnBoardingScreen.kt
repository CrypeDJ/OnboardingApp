package com.crype.onboardingapp.presentation.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.crype.onboardingapp.domain.model.OnboardingPageModel
import kotlin.io.path.ExperimentalPathApi



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen (
    navController: NavHostController
    ) {
    val pages = listOf(
        OnboardingPageModel.FirstPage,
        OnboardingPageModel.SecondPage,
        OnboardingPageModel.ThirdPage,
        OnboardingPageModel.FourthPage
        )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    Column (modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }

    }

}




