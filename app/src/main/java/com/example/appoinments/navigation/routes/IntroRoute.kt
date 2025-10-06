package com.example.appoinments.navigation.routes


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appoinments.feature.intro.IntroScreen
import com.example.appoinments.navigation.Screen

fun NavGraphBuilder.introRoute(onStart: ()-> Unit){
    composable(route = Screen.Intro.route){
        IntroScreen(onStartClick = onStart)
    }
}