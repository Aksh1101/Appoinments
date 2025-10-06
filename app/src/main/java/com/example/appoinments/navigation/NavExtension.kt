package com.example.appoinments.navigation

import androidx.navigation.NavController
import com.example.appoinments.core.model.DoctorModel

fun NavController.navigateToDetail(doctor : DoctorModel){
    currentBackStackEntry?.savedStateHandle?.set("doctor",doctor)
    navigate(Screen.Detail.route)
}