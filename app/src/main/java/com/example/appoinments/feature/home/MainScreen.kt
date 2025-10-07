package com.example.appoinments.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appoinments.core.model.DoctorModel
import com.example.appoinments.core.viewModel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel,
               onOpenTopDoctors : () -> Unit,
               onOpenDoctorDetail: (DoctorModel) -> Unit){

    val categories by viewModel.category.observeAsState(initial = emptyList())
    val doctors by viewModel.doctor.observeAsState(initial = emptyList())



    LaunchedEffect(Unit) {
        if (categories.isEmpty()) viewModel.loadCategory()
        if (doctors.isEmpty()) viewModel.loadDoctor()
    }

    var selectedBottom by remember { mutableStateOf(0) }

    Scaffold(containerColor = Color.White,
        bottomBar = {
            HomeButtonBar(
            selected = selectedBottom,
            onSelect = {selectedBottom = it}
        )}
    ){ innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item{ HomeHeader() }
            item { Banner() }
            item { SelectionHeader(title = "Doctor Speciality", onSeeAll = null)  }
            item { CategoryRow(items = categories , onClick = {}) }
            item { SelectionHeader(title = "Top Doctors", onSeeAll = onOpenTopDoctors)  }
            item { DoctorRow(items = doctors, onCardClick = onOpenDoctorDetail)}




        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    val viewModel : MainViewModel = viewModel()
    MainScreen(viewModel = viewModel, onOpenTopDoctors = {}, onOpenDoctorDetail = {})
}