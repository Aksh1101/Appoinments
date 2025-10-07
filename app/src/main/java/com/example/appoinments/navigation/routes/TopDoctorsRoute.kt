package com.example.appoinments.navigation.routes

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.appoinments.core.model.DoctorModel
import com.example.appoinments.core.viewModel.MainViewModel
import com.example.appoinments.feature.topDoctors.TopDoctorsScreen
import com.example.appoinments.navigation.Screen

fun NavGraphBuilder.topDoctorsRoute(
    vm: MainViewModel,
    onBack:()-> Unit,
    onOpenDetail:(DoctorModel)-> Unit
){
    composable(Screen.TopDoctors.route){
        val doctors by vm.doctor.observeAsState(emptyList())
        LaunchedEffect(key1 = Unit) {
            if (doctors.isEmpty())
                vm.loadDoctor()
        }

        TopDoctorsScreen(
            doctors = doctors,
            onBack = onBack,
            onOpenDetail = onOpenDetail,
        )

    }
}