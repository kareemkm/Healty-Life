package com.task.healthylife.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.task.healthylife.R

@Composable
fun SplashScreen(){

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("health_life_splash.json"))
    val progression by animateLottieCompositionAsState(composition = composition)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D8C5E)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.padding(40.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.splash),
                contentDescription = null
            )
        }
    }
}