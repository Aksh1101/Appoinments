package com.example.appoinments.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.appoinments.core.viewModel.MainViewModel
import com.example.appoinments.navigation.routes.detailRoute
import com.example.appoinments.navigation.routes.homeRoute
import com.example.appoinments.navigation.routes.introRoute
import com.example.appoinments.navigation.routes.topDoctorsRoute

@Composable
fun AppNavGraph(
    nav : NavHostController,
    vm : MainViewModel
){
    NavHost(navController = nav, startDestination = Screen.Intro.route){
        introRoute(
            onStart = {
                nav.navigate(Screen.Home.route){
                    popUpTo(Screen.Intro.route){
                        inclusive = true
                    }
                }
            }
        )
        homeRoute(
            vm = vm,
            onOpenTopDoctors = { nav.navigate(Screen.TopDoctors.route) },
            OnOpenDetail = {doctorModel -> nav.navigateToDetail(doctorModel)},
        )

        detailRoute(
            nav = nav,
            onBack = {nav.popBackStack()}
        )
        topDoctorsRoute(vm = vm,
            onBack = {nav.popBackStack()},
            onOpenDetail = {doctorModel -> nav.navigateToDetail(doctorModel)})


    }
}
