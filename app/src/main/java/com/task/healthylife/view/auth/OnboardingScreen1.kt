package com.task.healthylife.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.healthylife.R

@Composable
fun OnboardingScreen1(){
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Image(
            painter = painterResource(R.drawable.undraw_indoor_bike_pwa4_1),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp, top = 150.dp)
        )

        Image(
            painter = painterResource(R.drawable.undraw_hamburger_re_7sfy_1),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 30.dp)
        )
        Text(
            text = "The app offers you a balanced diet plan and a personalized training program to support your health and fitness",
            fontSize = 18.sp,
            color = Color(0xFF3BA170),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            lineHeight = 40.sp,

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 170.dp, start = 40.dp, end = 40.dp)
        )
    }

}