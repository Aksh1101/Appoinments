package com.example.appoinments.navigation.routes

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import android.net.Uri
import com.example.appoinments.core.model.DoctorModel
import com.example.appoinments.feature.detail.DetailScreen
import com.example.appoinments.navigation.Screen

fun NavGraphBuilder.detailRoute(
    nav : NavHostController,
    onBack: () -> Unit){

    composable(route = Screen.Detail.route){
        backStackEntry : NavBackStackEntry ->
        val context = LocalContext.current
        val prevEntry = remember(nav) {nav.previousBackStackEntry}
        val doctor = remember(prevEntry) {
            prevEntry?.savedStateHandle?.get<DoctorModel>("doctor")
        }

        LaunchedEffect(prevEntry,doctor) {
            if (doctor == null){
                onBack()
            }else{
                prevEntry?.savedStateHandle?.remove<DoctorModel>("doctor")
            }
        }
        if (doctor != null) {
            DetailScreen(
                item = doctor,
                onBack = onBack,
                onOpenWebsite = {url->
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                onSendSms = {mobile,body->
                    context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$mobile"))
                        .apply { putExtra("sms_body", body) }
                    )
                },
                onDial = {mobile ->
                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${mobile.trim()}")))
                },
                onDirection = {location->
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(location)))
                },
                onShare = {subject, text ->
                    context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND)
                        .apply { type = "text.plain"
                            putExtra(Intent.EXTRA_SUBJECT, subject)
                            putExtra(Intent.EXTRA_TEXT, text)
                        }, "choose one"))
                }

            )
        }else{
            Spacer(modifier = Modifier.height(1.dp))

        }

        }


}