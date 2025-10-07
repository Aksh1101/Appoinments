package com.example.appoinments.navigation.routes

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appoinments.core.model.DoctorModel
import com.example.appoinments.core.viewModel.MainViewModel
import com.example.appoinments.feature.home.MainScreen
import com.example.appoinments.navigation.Screen


fun NavGraphBuilder.homeRoute(
    vm : MainViewModel,
    onOpenTopDoctors : () -> Unit,
    OnOpenDetail : (DoctorModel) -> Unit
){
    composable(route = Screen.Home.route){
        val categories by vm.category.observeAsState(initial = emptyList())
        val doctors by vm.doctor.observeAsState(initial = emptyList())


        LaunchedEffect(key1 = Unit) {
            if (categories.isEmpty()) vm.loadCategory()
            if (doctors.isEmpty()) vm.loadDoctor()
        }
        MainScreen(viewModel = vm,
            onOpenTopDoctors = onOpenTopDoctors,
        onOpenDoctorDetail = OnOpenDetail)
    }

}

